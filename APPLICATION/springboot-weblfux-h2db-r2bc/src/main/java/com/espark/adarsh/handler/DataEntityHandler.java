package com.espark.adarsh.handler;

import com.espark.adarsh.entity.DataEntity;
import com.espark.adarsh.repository.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataEntityHandler {

    private final DataEntityRepository dataEntityRepository;

    public Mono<ServerResponse> fetchData(ServerRequest serverRequest) {
        Flux<DataEntity> dataEntityFlux = this.dataEntityRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("Cache-Control","No")
                .body(dataEntityFlux, DataEntity.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }

    public Mono<ServerResponse> fetchDataStream(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .body(dataEntityRepository.findAll(), DataEntity.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
