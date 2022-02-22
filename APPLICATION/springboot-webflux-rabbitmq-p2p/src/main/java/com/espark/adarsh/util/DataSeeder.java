package com.espark.adarsh.util;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;


@Slf4j
@Component
public class DataSeeder {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        RabbitConfigurationProp.Configuration configuration = rabbitConfigurationProp.getQueues().get("espark-message-queue");
        if (configuration != null) {
            Flux.range(0, Integer.MAX_VALUE)
                    .delayElements(Duration.ofSeconds(1))
                    .doOnNext(index -> log.info("Processing Index {}", index))
                    .map(index -> {
                        amqpTemplate.convertAndSend(configuration.getExchange(), configuration.getRoutingKey(), "sample data " + LocalDate.now());
                        return Mono.just("send data to queue");
                    })
                    .doOnNext(dataEntity -> log.info("Data Return {}", dataEntity))
                    .filter(dataEntity -> dataEntity != null);
        }
    }
}
