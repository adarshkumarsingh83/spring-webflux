package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MessagePublisherService {

    @Autowired
    KafkaSender<String, String> kafkaSender;

    @Value("${kafka.message}")
    String kafkaMessage;

    @Value("${kafka.key}")
    String kafkaKey;

    @Value("${kafka.topic}")
    String kafkaTopic;

    @EventListener(ApplicationContextEvent.class)
    public void init() {
        Flux.interval(Duration.ofSeconds(1))
                .map(index -> kafkaSender.send(
                                Mono.just(SenderRecord.create(
                                        new ProducerRecord<String, String>(kafkaTopic, kafkaKey, index + " " + kafkaMessage + LocalDateTime.now()), "id-" + index)))
                        .log()
                        .doOnNext(e -> log.info("doOnNext() id:" + e.correlationMetadata() + " topic:" + e.recordMetadata().topic()))
                        .doOnError(exception -> log.error(exception.getLocalizedMessage()))
                        .subscribe(objectSenderResult -> {
                            log.info("subscribe() id:" + objectSenderResult.correlationMetadata() + " topic:" + objectSenderResult.recordMetadata().topic());
                        }))
                .subscribe();
    }
}
