package com.espark.adarsh;

import com.espark.adarsh.bean.EventBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@EnableAsync
@SpringBootApplication
public class WebfluxClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebfluxClientApplication.class, args);
    }

    @Bean
    WebClient webClient() {
        return WebClient.create("http://localhost:8080/api");
    }

    @Bean
    ApplicationRunner runner(WebClient webClient) {
        return args -> webClient.get()
                .uri("/event")
                .retrieve()
                .bodyToFlux(EventBean.class)
                .subscribe(data -> log.info(data.toString()));
    }
}
