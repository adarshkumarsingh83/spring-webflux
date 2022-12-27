package com.espark.adarsh.integration;

import com.espark.adarsh.entity.Employee;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@HttpExchange(url = "/api", accept = "application/json", contentType = "application/json")
public interface EmployeeService {

    @GetExchange("/employees")
    Flux<Employee> getAllEmployee();

    @GetExchange("/employee/{id}")
    public Mono<Employee> getEmployee(@PathVariable("id") Long id);

    @DeleteExchange("/employee/{id}")
    public Mono<Employee> removeEmployee(@PathVariable("id") Long id);

    @PostExchange("/employee")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee);

    @GetExchange("/employee/{id}")
    public Mono<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee);

    @PatchExchange("/employee/{id}")
    public Mono<Employee> updatePartialEmployee(@PathVariable("id") Long id, @RequestBody Map<String, Object> employee);

}
