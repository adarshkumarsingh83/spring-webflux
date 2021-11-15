package com.espark.adarsh.handler;

import com.espark.adarsh.bean.DataBean;
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

    public Mono<ServerResponse> dataBeanList(ServerRequest serverRequest){
        int limit = (int) serverRequest.attribute("limit").orElseGet(() -> 10); // for request param 
        //int limit = Integer.parseInt(serverRequest.pathVariable("limit")); // for path variable
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(dataService.dataBeanList(limit), DataBean.class);
    }

    public Mono<ServerResponse> dataBeanStream(ServerRequest serverRequest){
        int limit = (int) serverRequest.attribute("limit").orElseGet(() -> 10); // for request param
        //int limit = Integer.parseInt(serverRequest.pathVariable("limit")); // for path variable
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(dataService.dataBeanStream(limit), DataBean.class);
    }
}
