package com.espark.adarsh.router;

import com.espark.adarsh.handler.DataEntityHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Component

public class DataEntityRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(DataEntityHandler dataEntityHandler) {
        return RouterFunctions.route()
                .GET("/router/data", dataEntityHandler::fetchData)
                .GET("/router/stream", dataEntityHandler::fetchDataStream)
                .build();
    }
}
