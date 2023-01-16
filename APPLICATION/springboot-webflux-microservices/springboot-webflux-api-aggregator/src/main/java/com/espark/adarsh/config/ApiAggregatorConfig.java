package com.espark.adarsh.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class ApiAggregatorConfig {

    @Bean("employeeWebClient")
    WebClient employeeWebClient() {
        return WebClient.builder()
                .baseUrl("http://EMPLOYEE-SERVICE")
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector())
                .defaultHeader("employee-request", "ESPARK-HEADER-FROM-API-SERVICE")
                .build();
    }

    @Bean("addressWebClient")
    WebClient addressWebClient() {
        return WebClient.builder()
                .baseUrl("http://ADDRESS-SERVICE")
                .defaultCookie("cookie-name", "cookie-value")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(connector())
                .defaultHeader("address-request", "ESPARK-HEADER-FROM-API-SERVICE")
                .build();
    }


    public ClientHttpConnector connector() {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                                .doOnConnected(conn -> conn
                                        .addHandlerLast(new ReadTimeoutHandler(10))
                                        .addHandlerLast(new WriteTimeoutHandler(10))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        return connector;
    }
}
