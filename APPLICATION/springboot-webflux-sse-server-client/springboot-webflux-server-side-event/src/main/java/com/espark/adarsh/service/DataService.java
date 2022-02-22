package com.espark.adarsh.service;

import com.espark.adarsh.bean.MessageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@Slf4j
@Service
public class DataService {


    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .doOnNext(index -> log.info("processing index in flux {}", index))
                .map(sequence -> " Welcome to Espark from Flux -> " + LocalTime.now().toString());

    }

    public Flux<MessageBean<String>> streamFluxObject() {
        return Flux.interval(Duration.ofSeconds(1))
                .doOnNext(index -> log.info("processing index in flux stream {}", index))
                .map(sequence -> {
                    return MessageBean.<String>builder()
                            .id(sequence)
                            .event("flux-stream-event")
                            .data(" Welcome to Espark from Flux -> " + LocalTime.now().toString())
                            .build();
                });
    }

    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .doOnNext(index -> log.info("processing index in sse {}", index))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("sse-periodic-event")
                        .data(" Welcome to Espark from SSE - " + LocalTime.now().toString())
                        .build());
    }
}
