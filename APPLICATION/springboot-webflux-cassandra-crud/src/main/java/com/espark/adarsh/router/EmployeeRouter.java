package com.espark.adarsh.router;



import com.espark.adarsh.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class EmployeeRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(EmployeeHandler employeeHandler) {
        return RouterFunctions.route()
                .GET("/router/employee/{empId}", employeeHandler::getEmployee)
                .GET("/router/employees", employeeHandler::getEmployees)
                .POST("/router/employee", employeeHandler::saveEmployee)
                .PUT("/router/employee/{empId}", employeeHandler::updateEmployee)
                .DELETE("/router/employee/{empId}", employeeHandler::deleteEmployee)
                .build();

    }
}
