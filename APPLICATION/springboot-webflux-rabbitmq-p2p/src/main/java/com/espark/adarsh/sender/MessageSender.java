package com.espark.adarsh.sender;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.OutboundMessageResult;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Sender;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class MessageSender {

    @Autowired
    Sender sender;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    public void send(String[] message, CountDownLatch latch) {
        Flux<OutboundMessage> outboundMessageFlux = Flux.range(0, message.length)
                .map(item -> new OutboundMessage(rabbitConfigurationProp.getQueues().get(0).getExchange()
                        , rabbitConfigurationProp.getQueues().get(0).getRoutingKey()
                        , message[item].getBytes()));
        sender.sendWithPublishConfirms(outboundMessageFlux)
                .doOnError(exception -> log.error("Message Publishing Failed {}", exception))
                .subscribe(outboundMessageResult -> {
                    if (outboundMessageResult.isAck()) {
                        log.info("Message Published {} sent successfully", new String(outboundMessageResult.getOutboundMessage().getBody()));
                        latch.countDown();
                    }
                });
    }

    public void close() {
        this.sender.close();
    }
}
