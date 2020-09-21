package com.espark.adarsh.service;

import com.espark.adarsh.bean.DataHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@Slf4j
@Service
public class GreetingWebClient {

    private WebClient client = WebClient.create("http://localhost:8080");

    public String getResult(String type, String name) {
        log.info("GreetingWebClient getResult() {}", type);
        if (type.equalsIgnoreCase("admin")) {
            Mono<ClientResponse> adminResult = client.get()
                    .uri("/admin?name=" + name)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange();
            String response = ">> result = " + adminResult.flatMap(res -> res.bodyToMono(DataHolder.class)).block().getData();
            log.info(response);
            return response;
        } else if (type.equalsIgnoreCase("user")) {
            Mono<ClientResponse> userResult = client.get()
                    .uri("/user?name=" + name)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange();
            String response = ">> result = " + userResult.flatMap(res -> res.bodyToMono(DataHolder.class)).block().getData();
            log.info(response);
            return response;
        } else {
            Mono<ClientResponse> guestResult = client.get()
                    .uri("/guest?name=" + name)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange();
            String response = ">> result = " + guestResult.flatMap(res -> res.bodyToMono(DataHolder.class)).block().getData();
            log.info(response);
            return response;
        }
    }
}