package com.espark.adarsh.service.sender;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.util.List;

@Slf4j
@Service
public class MessageSenderService {

    @Autowired
    Sender sender;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    public Flux<String> sendMessageToRabbitMq(List<String> message) {
        ReactorRabbitMq
        Flux<OutboundMessage> outboundMessageFlux = Flux.range(0, message.size())
                .map(item -> new OutboundMessage(rabbitConfigurationProp.getQueues().get(0).getExchange()
                        , rabbitConfigurationProp.getQueues().get(0).getRoutingKey()
                        , message.get(item).getBytes()));
        return sender.sendWithPublishConfirms(outboundMessageFlux)
                .doOnError(exception -> log.error("Message Publishing Failed {}", exception))
                .map(outboundMessageResult -> {
                    String status = "Message Published is Unsuccessful ";
                    if (outboundMessageResult.isAck()) {
                        status = new String(outboundMessageResult.getOutboundMessage().getBody());
                        log.info("Message Published {} sent successfully", status);
                    } else {
                        log.info("Message Published {} sent successfully", status);
                    }
                    return status;
                });
    }

    public void close() {
        this.sender.close();
    }
}
