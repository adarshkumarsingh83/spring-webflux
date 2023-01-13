package com.espark.adarsh.handler;

import com.espark.adarsh.bean.EmployeeRecord;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;

    public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {
        log.info("EmployeeHandler::saveEmployee ");
        return serverRequest.bodyToMono(EmployeeRecord.class)
                .flatMap(employeeRecord -> employeeService.saveEmployee(employeeRecord))
                .flatMap(employeeRecordMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeRecordMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));

    }

    public Mono<ServerResponse> updateEmployee(ServerRequest serverRequest) {
        Long employeeId = Long.valueOf(serverRequest.pathVariable("employeeId"));
        log.info("EmployeeHandler::updateEmployee {}", employeeId);
        return serverRequest.bodyToMono(EmployeeRecord.class)
                .flatMap(employeeRecord -> employeeService.updateEmployee(employeeId, employeeRecord))
                .flatMap(employeeRecordMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(employeeRecordMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));

    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest serverRequest) {
        Long employeeId = Long.valueOf(serverRequest.pathVariable("employeeId"));
        log.info("EmployeeHandler::deleteEmployee  {}", employeeId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.deleteEmployee(employeeId), EmployeeRecord.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployee(ServerRequest serverRequest) {
        Long employeeId = Long.valueOf(serverRequest.pathVariable("employeeId"));
        log.info("EmployeeHandler::getEmployee  {}", employeeId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.getEmployee(employeeId), EmployeeRecord.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        log.info("EmployeeHandler::getEmployees  ");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.getEmployees(), EmployeeRecord.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
