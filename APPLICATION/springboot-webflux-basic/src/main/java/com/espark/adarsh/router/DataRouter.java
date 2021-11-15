package com.espark.adarsh.router;

import com.espark.adarsh.handler.DataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class DataRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(DataHandler dataHandler) {
        return RouterFunctions.route()
                .GET("/data", RequestPredicates.accept(MediaType.APPLICATION_JSON), dataHandler::getMonoData)
                .GET("/data-list", RequestPredicates.accept(MediaType.APPLICATION_JSON), dataHandler::getFluxData)
                .build();

    }
}
