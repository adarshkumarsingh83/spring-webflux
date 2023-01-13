package com.espark.adarsh.router;


import com.espark.adarsh.handler.AddressHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AddressRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(AddressHandler addressHandler) {
        return RouterFunctions.route()
                .GET("/router/address/{addressId}", addressHandler::getAddress)
                .GET("/router/addresses", addressHandler::getAddresses)
                .POST("/router/address", addressHandler::saveAddress)
                .PUT("/router/address/{addressId}", addressHandler::updateAddress)
                .DELETE("/router/address/{addressId}", addressHandler::updateAddress)
                .build();

    }

}
