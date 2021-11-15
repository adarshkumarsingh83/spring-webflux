package com.espark.adarsh.handler;

import com.espark.adarsh.bean.DataBean;
import com.espark.adarsh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class DataHandler {

    @Autowired
    DataService dataService;

    public Mono<ServerResponse> getMonoData(ServerRequest request) {
        int index = (int) request.attribute("index").orElseGet(() -> 0);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(dataService.dataBeanMono(index), DataBean.class));
    }

    public Mono<ServerResponse> getFluxData(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(dataService.dataBeanFlux(), DataBean.class));
    }
}
