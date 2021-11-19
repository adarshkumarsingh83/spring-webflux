package com.espark.adarsh.router;

import com.espark.adarsh.handler.MessageHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class MessageRouter {

    public RouterFunction<ServerResponse> routerFunction(MessageHandler messageHandler) {
        return RouterFunctions.route()
                .GET("/router/messages", messageHandler::subscribeMessage)
                .POST("/router/messages", messageHandler::publishMessage)
                .build();
    }

}
