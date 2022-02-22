package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

@Slf4j
@Service
public class ApplicationService {

    static WebClient client = WebClient.create("http://localhost:8080");
    static ParameterizedTypeReference<ServerSentEvent<String>> type
            = new ParameterizedTypeReference<ServerSentEvent<String>>() {
    };

    @Async
    public void triggerSse() {

        Flux<ServerSentEvent<String>> eventStream = client.get()
                .uri("/router/data-stream-sse")
                .retrieve()
                .bodyToFlux(type);

        eventStream.subscribe(
                content -> log.info("Time: {} - event: name[{}], id [{}], content[{}] ",
                        LocalTime.now(), content.event(), content.id(), content.data()),
                error -> log.error("Error receiving SSE: {}", error),
                () -> log.info("Completed!!!"));
    }

    @Async
    public void triggerFluxStream() {

        Flux<String> eventStream = client.get()
                .uri("/router/data-stream-flux")
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<String>() {
                });

        eventStream.subscribe(
                content -> log.info("Time: {} - content: {} ",
                        LocalTime.now(), content),
                error -> log.error("Error receiving SSE: {}", error),
                () -> log.info("Completed!!!"));
    }
}
