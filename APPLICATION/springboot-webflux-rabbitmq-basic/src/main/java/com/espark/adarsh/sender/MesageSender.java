package com.espark.adarsh.sender;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.OutboundMessageResult;
import reactor.rabbitmq.QueueSpecification;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Sender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MesageSender {

    private static final String QUEUE = "espark-queue";

    private final Sender sender;

    public MesageSender() {
        this.sender = RabbitFlux.createSender();
    }

    public void send(String queue, int count, CountDownLatch latch) {
        Flux<OutboundMessageResult> confirmations = sender.sendWithPublishConfirms(Flux.range(1, count)
                .map(i -> new OutboundMessage("", queue, ("Message_" + i).getBytes())));

        sender.declareQueue(QueueSpecification.queue(queue))
                .thenMany(confirmations)
                .doOnError(e -> log.error("Send failed", e))
                .subscribe(r -> {
                    if (r.isAck()) {
                        log.info("Message {} sent successfully", new String(r.getOutboundMessage().getBody()));
                        latch.countDown();
                    }
                });
    }

    public void close() {
        this.sender.close();
    }

    public static void main(String[] args) throws Exception {
        int count = 20;
        CountDownLatch latch = new CountDownLatch(count);
        MesageSender sender = new MesageSender();
        sender.send(QUEUE, count, latch);
        latch.await(10, TimeUnit.SECONDS);
        sender.close();
    }

}
