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
                .GET("/router/data-stream-flux", dataHandler::streamFlux)
                .GET("/router/data-stream-flux-object", dataHandler::streamFluxObject)
                .GET("/router/data-stream-sse", dataHandler::streamEvents)
                .build();

    }
}