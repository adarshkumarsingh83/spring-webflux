package com.espark.adarsh.service.reciver;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

import org.springframework.amqp.rabbit.listener.MessageListenerContainer;

@Slf4j
@Service
public class MessageReceiverService {

    @Autowired
    private MessageListenerContainer messageListenerContainer;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    public Flux<String> consumeDataFromRabbitMq() {

        Flux<String> f = Flux.<String>create(emitter -> {
            log.info("Added listener to , queName={}", rabbitConfigurationProp.getQueueName());
            messageListenerContainer.setupMessageListener((MessageListener) message -> {
                String queName = message.getMessageProperties().getConsumerQueue();
                if (emitter.isCancelled()) {
                    messageListenerContainer.stop();
                    log.info("Cancelled queName = {}", queName);
                    return;
                }
                String payload = new String(message.getBody());
                emitter.next(payload);
                log.info("Message send to client queName = {}", queName);
            });

            emitter.onRequest((v -> {
                log.info("Starting Container queName = {}", rabbitConfigurationProp.getQueueName());
                messageListenerContainer.start();
            }));

            emitter.onDispose((() -> {
                log.info("Disposing Container queName = {}", rabbitConfigurationProp.getQueueName());
                messageListenerContainer.stop();
            }));
        });

        return Flux.interval(Duration.ofSeconds(1))
                .map(v -> {
                    log.info("Sending keep alive Message");
                    return "Keep alive Message";
                }).mergeWith(f);
    }

}
