package com.espark.adarsh.services;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EmployeeIntegrationService {

    private WebClient client = WebClient.create("http://localhost:8080/api");

    public Employee getById(Long employeeId) {
        return client
                .get()
                .uri("/employee/{employeeId}", employeeId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new ApplicationException("400 Status Code ")))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ApplicationException("500 Status Code ")))
                .bodyToMono(Employee.class)
                .doOnSuccess(employee1 -> {
                    log.info("Fetch successful " + employee1.toString());
                }).block();
    }

    public Employee saveEmployee(Employee employee) {
        return client
                .post()
                .uri("/employee")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new ApplicationException("400 Status Code ")))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ApplicationException("500 Status Code ")))
                .bodyToMono(Employee.class)
                .doOnSuccess(employee1 -> {
                    log.info("Create successful " + employee1.toString());
                }).block();
    }

    public Employee updateEmployee(Long employeeId, Employee employee) {
        return client
                .put()
                .uri("/employee/{employeeId}", employeeId)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(employee))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new ApplicationException("400 Status Code ")))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ApplicationException("500 Status Code ")))
                .bodyToMono(Employee.class)
                .doOnSuccess(employee1 -> {
                    log.info("Update successful " + employee1.toString());
                }).block();
    }

    public Employee deleteEmployee(Long employeeId) {
        return client
                .delete()
                .uri("/api/employee/{employeeId}", employeeId)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new ApplicationException("400 Status Code ")))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ApplicationException("500 Status Code ")))
                .bodyToMono(Employee.class)
                .doOnSuccess(employee1 -> {
                    log.info("Delete successful " + employee1.toString());
                }).block();
    }

    public Flux<Employee> getAllEmployee() {
        return this.client
                .get()
                .uri("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,clientResponse -> Mono.error(new ApplicationException("400 Status Code ")))
                .onStatus(HttpStatus::is5xxServerError,clientResponse -> Mono.error(new ApplicationException("500 Status Code ")))
                .bodyToFlux(Employee.class)
                .doOnNext(employees -> {
                    log.info("Fetch All successful " + employees.toString());
                });
    }
}
