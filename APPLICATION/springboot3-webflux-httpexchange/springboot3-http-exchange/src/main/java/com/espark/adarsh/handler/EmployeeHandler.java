package com.espark.adarsh.handler;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.integration.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Slf4j
@Component
public class EmployeeHandler {

    @Autowired
    EmployeeService employeeService;

    public Mono<ServerResponse> getMonoEmployeeData(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("EmployeeHandler::getMonoEmployeeData {} ", id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(employeeService.getEmployee(Long.parseLong(id)), Employee.class));
    }

    public Mono<ServerResponse> getFluxEmployeeData(ServerRequest request) {
        log.info("EmployeeHandler::getFluxEmployeeData ");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(employeeService.getAllEmployee(), Employee.class));
    }

    public Mono<ServerResponse> deleteMonoEmployeeData(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("EmployeeHandler::deleteMonoEmployeeData {} ", id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromProducer(employeeService.removeEmployee(Long.parseLong(id)), Employee.class));
    }

    public Mono<ServerResponse> updateMonoEmployeeData(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("EmployeeHandler::updateMonoEmployeeData {} ", id);
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        return employeeMono.flatMap(employee -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.updateEmployee(Long.parseLong(id), employee), Employee.class));
    }


    public Mono<ServerResponse> saveMonoEmployeeData(ServerRequest request) {
        log.info("EmployeeHandler::saveMonoEmployeeData  ");
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        return employeeMono.flatMap(employee -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.saveEmployee(employee), Employee.class));
    }

    public Mono<ServerResponse> updatePartialMonoEmployeeData(ServerRequest request) {
        String id = request.pathVariable("id");
        log.info("EmployeeHandler::updatePartialMonoEmployeeData {} ", id);
        Mono<HashMap> employeeMono = request.bodyToMono(HashMap.class);
        return employeeMono.flatMap(employee -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeService.updatePartialEmployee(Long.parseLong(id), employee), Employee.class));
    }

}

