package com.espark.adarsh.router;

import com.espark.adarsh.handler.DataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class DataRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(DataHandler dataHandler) {
        return RouterFunctions.route()
                .GET("/router/data-list", dataHandler::dataBeanList)
                .GET("/router/data-stream", dataHandler::dataBeanStream)
                .GET("/router/data-list/{limit}", dataHandler::dataBeanListPathVariable)
                .GET("/router/data-stream/{limit}", dataHandler::dataBeanStreamPathVariable)
                .build();

    }
}