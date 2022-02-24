package com.espark.adarsh.handler;

import com.espark.adarsh.service.KafkaDataConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KafkaDataHandler {

    @Autowired
    KafkaDataConsumerService kafkaDataConsumerService;

    public Mono<ServerResponse> fetchDataStream(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .header("Cache-Control","no-cache")
                .header("Connection","keep-alive")
                .body(kafkaDataConsumerService.getStreamData(),String.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
