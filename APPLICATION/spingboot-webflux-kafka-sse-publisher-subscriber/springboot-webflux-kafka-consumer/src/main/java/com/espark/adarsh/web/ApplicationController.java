package com.espark.adarsh.web;

import com.espark.adarsh.service.KafkaDataConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    KafkaDataConsumerService kafkaDataConsumerService;

    @GetMapping(value = "/controller/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getKafkaStreamData() {
        log.info("ApplicationController getKafkaStreamData()");
        return kafkaDataConsumerService.getStreamData();
    }

    @GetMapping(value = "/controller/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getKafkaStreamDataSSE() {
        log.info("ApplicationController getKafkaStreamData()");
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<String> stringFlux = kafkaDataConsumerService.getStreamData();
        return Flux.zip(interval, stringFlux).map(tuple ->
                ServerSentEvent.<String>builder()
                        .event("event")
                        .data(tuple.getT2())
                        .build()
        );
    }
}
