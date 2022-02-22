package com.espark.adarsh.handler;

import com.espark.adarsh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DataHandler {

    @Autowired
    DataService dataService;

    public Mono<ServerResponse> streamFlux(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(dataService.streamFlux(), String.class);
    }

    public Mono<ServerResponse> streamEvents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(dataService.streamEvents(), String.class);
    }
}
