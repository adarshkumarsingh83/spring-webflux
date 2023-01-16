package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    @Qualifier("employeeWebClient")
    WebClient employeeWebClient;


    public Mono<EmployeeRecord> getEmployee(Long id) {
        return employeeWebClient.get()
                .uri("/employee/" + id)
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())
                .bodyToMono(EmployeeRecord.class);

    }

    public Flux<EmployeeRecord> getEmployees() {
        return employeeWebClient.get()
                .uri("/employees")
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())
                .bodyToFlux(EmployeeRecord.class);
    }

    public Mono<EmployeeRecord> saveEmployee(EmployeeRecord employeeRecord) {
        return employeeWebClient.post()
                .uri("/employee")
                .body(Mono.just(employeeRecord), EmployeeRecord.class)
                .retrieve()
                .bodyToMono(EmployeeRecord.class);
    }

    public Mono<EmployeeRecord> updateEmployee(Long id, EmployeeRecord employeeRecord) {
        return employeeWebClient.put()
                .uri("/employees/" + id)
                .body(Mono.just(employeeRecord), EmployeeRecord.class)
                .retrieve()
                .bodyToMono(EmployeeRecord.class);
    }

    public Mono<EmployeeRecord> delete(Long id) {
        return employeeWebClient.delete()
                .uri("/employee/" + id)
                .retrieve()
                .bodyToMono(EmployeeRecord.class);
    }

}
