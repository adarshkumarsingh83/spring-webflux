package com.espark.adarsh.services;

import com.espark.adarsh.bean.EventBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ServerIntegrationService {

    private WebClient client = WebClient.create("http://localhost:8080/api");

    public void serverLiveStatus(){
        this.client
                .get()
                .uri("/event")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .exchange()
                .flatMap(evt -> evt.bodyToMono(EventBean.class))
                .subscribe(System.out::println);
    }
}
