package com.espark.adarsh.handler;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;

    public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(EmployeeBean.class)
                .flatMap(employeeBean -> employeeService.saveEmployee(employeeBean))
                .flatMap(employeeBeanMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeBeanMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {

        return serverRequest.bodyToMono(EmployeeBean.class)
                .flatMap(employeeBean -> employeeService.updateEmployee(employeeBean.getId(), employeeBean))
                .flatMap(employeeBeanMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeBeanMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {
        int employeeId = Integer.valueOf(serverRequest.pathVariable("empId"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.deleteEmployee(employeeId), EmployeeBean.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployee(ServerRequest serverRequest) {
        int employeeId = Integer.valueOf(serverRequest.pathVariable("empId"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.getEmployee(employeeId), EmployeeBean.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        Flux<EmployeeBean> employees = employeeService.getEmployees();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employees, EmployeeBean.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
