package com.espark.adarsh.handler;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;

    public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Employee.class)
                .flatMap(employee -> employeeService.saveEmployee(employee))
                .flatMap(employeeMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(Employee.class)
                .flatMap(employee -> employeeService.updateEmployee(employee.getId(), employee))
                .flatMap(employeeMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {
        int employeeId = Integer.valueOf(serverRequest.pathVariable("empId"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.deleteEmployee(employeeId), Employee.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployee(ServerRequest serverRequest) {
        int employeeId = Integer.valueOf(serverRequest.pathVariable("empId"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.getEmployee(employeeId), Employee.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        Flux<Employee> employees = employeeService.getEmployees();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employees, Employee.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
