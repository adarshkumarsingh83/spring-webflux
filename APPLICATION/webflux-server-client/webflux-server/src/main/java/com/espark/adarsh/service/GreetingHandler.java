package com.espark.adarsh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class GreetingHandler {

    public Mono<ServerResponse> greetAdmin(ServerRequest request) {
        String name = null;
        if (request.queryParam("name").isPresent()) {
            name = request.queryParam("name").get();
        }
        log.info("GreetingHandler.greetAdmin()");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Hello, Admin! " + name);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.getJson(data)));
    }

    public Mono<ServerResponse> greetUser(ServerRequest request) {
        String name = null;
        if (request.queryParam("name").isPresent()) {
            name = request.queryParam("name").get();
        }
        log.info("GreetingHandler.greetUser()");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Hello, User! " + name);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.getJson(data)));
    }

    public Mono<ServerResponse> greetGuest(ServerRequest request) {
        String name = null;
        if (request.queryParam("name").isPresent()) {
            name = request.queryParam("name").get();
        }
        log.info("GreetingHandler.greetGuest()");
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Hello, Guest! " + name);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(this.getJson(data)));
    }


    private String getJson(Map<String, Object> data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return "{'message':'welcome}";
        }
    }
}
