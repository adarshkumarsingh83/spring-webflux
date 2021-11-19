package com.espark.adarsh.service.reciver;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.Receiver;
import java.time.Duration;

@Slf4j
@Service
public class MessageReceiverService {

    @Autowired
    Receiver receiver;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    public Flux<String> consumeDataFromRabbitMq() {
        Flux<String> response = receiver.consumeAutoAck(rabbitConfigurationProp.getQueueName())
                .delaySubscription(Duration.ofMillis(100))
                .map(byteMessage -> new String(byteMessage.getBody()))
                .doOnNext(message -> log.info("Received message {}", message));
        return response;
    }

    public void close() {
        this.receiver.close();
    }
}
