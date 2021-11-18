package com.espark.adarsh.receiver;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Disposable;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MessageReceiver {

    private static final String QUEUE = "espark-queue";

    private final Receiver receiver;
    private final Sender sender;

    public MessageReceiver() {
        this.receiver = RabbitFlux.createReceiver();
        this.sender = RabbitFlux.createSender();
    }

    public Disposable consume(String queue, CountDownLatch latch) {
        return receiver.consumeAutoAck(queue)
                .delaySubscription(sender.declareQueue(QueueSpecification.queue(queue)))
                .subscribe(m -> {
                    log.info("Received message {}", new String(m.getBody()));
                    latch.countDown();
                });
    }

    public void close() {
        this.sender.close();
        this.receiver.close();
    }

    public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        MessageReceiver receiver = new MessageReceiver();
        Disposable disposable = receiver.consume(QUEUE, latch);
        latch.await(10, TimeUnit.SECONDS);
        disposable.dispose();
        receiver.close();
    }

}