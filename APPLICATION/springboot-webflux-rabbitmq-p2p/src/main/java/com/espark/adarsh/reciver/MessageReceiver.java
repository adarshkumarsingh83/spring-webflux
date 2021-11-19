package com.espark.adarsh.reciver;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class MessageReceiver {


    @Autowired
    Sender sender;

    @Autowired
    Receiver receiver;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    public Disposable consume(CountDownLatch latch) {
        return receiver.consumeAutoAck(rabbitConfigurationProp.getQueueName())
                .delaySubscription(Duration.ofMillis(100))
                .subscribe(message -> {
                    log.info("Received message {}", new String(message.getBody()));
                    latch.countDown();
                });
    }

    public void close() {
        this.receiver.close();
    }
}
