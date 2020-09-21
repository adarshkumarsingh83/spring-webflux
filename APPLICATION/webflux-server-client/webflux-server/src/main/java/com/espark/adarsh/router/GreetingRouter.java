package com.espark.adarsh.router;

import com.espark.adarsh.service.GreetingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Configuration
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
       log.info("GreetingRouter.route()");
        return RouterFunctions
                .route(RequestPredicates.GET("/admin")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), greetingHandler::greetAdmin)
                .andRoute(RequestPredicates.GET("/user")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), greetingHandler::greetUser)
                .andRoute(RequestPredicates.GET("/guest")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), greetingHandler::greetGuest);
    }
}