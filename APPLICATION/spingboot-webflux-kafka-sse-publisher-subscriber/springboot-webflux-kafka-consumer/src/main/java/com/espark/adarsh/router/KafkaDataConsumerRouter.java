package com.espark.adarsh.router;

import com.espark.adarsh.handler.KafkaDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Component
public class KafkaDataConsumerRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(KafkaDataHandler kafkaDataHandler) {
        return RouterFunctions.route()
                .GET("/router/stream", kafkaDataHandler::fetchDataStream)
                .GET("/router/sse", kafkaDataHandler::fetchServerSideEvent)
                .build();
    }
}
