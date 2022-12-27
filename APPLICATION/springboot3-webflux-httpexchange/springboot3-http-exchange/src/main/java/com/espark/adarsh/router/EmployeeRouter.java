package com.espark.adarsh.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import com.espark.adarsh.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;


@Slf4j
@Configuration(proxyBeanMethods = false)
public class EmployeeRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(EmployeeHandler employeeHandler) {
        log.info("EmployeeRouter::routerFunction executed ");
        return RouterFunctions.route()
                .GET("/employees", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::getFluxEmployeeData)
                .GET("/employee/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::getMonoEmployeeData)
                .DELETE("/employee/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::deleteMonoEmployeeData)
                .PUT("/employee/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::updateMonoEmployeeData)
                .POST("/employee", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::saveMonoEmployeeData)
                .PATCH("/employee/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), employeeHandler::updatePartialMonoEmployeeData)
                .build();

      /* return RouterFunctions.route(RequestPredicates.GET("/employees")
               .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),employeeHandler::getFluxEmployeeData);*/
    }
}
