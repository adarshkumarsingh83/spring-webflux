package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/message")
    public String message(@RequestHeader(value = "employee-request", required = false) String header) {
        log.info("label=employee-controller message() executed ");
        return "Hello from Espark Employee Service " + header;
    }

    @GetMapping("/employee/{id}")
    public Mono<Employee> getById(@PathVariable("id") Long id) {
        log.info("label=employee-controller getById() executed ");
        return this.employeeService.getById(id);
    }

    @GetMapping("/employees")
    public Flux<Employee> getAllEmployee() {
        log.info("label=employee-controller getAllEmployee() executed ");
        return this.employeeService.getAllEmployee();
    }

    @DeleteMapping("/employee/{id}")
    public Mono<Employee> deleteById(@PathVariable("id") Long id) {
        log.info("label=employee-controller deleteById() executed ");
        return this.employeeService.deleteEmployee(id);
    }

    @PutMapping("/employee/{id}")
    public Mono<Employee> updateById(@PathVariable("id") Long id, @RequestBody Employee employee) {
        log.info("label=employee-controller updateById() executed ");
        return this.employeeService.updateEmployee(id, employee);
    }

    @PostMapping("/employee")
    public Mono<Employee> saveEmployee(@RequestBody Employee employee) {
        log.info("label=employee-controller saveEmployee() executed ");
        return this.employeeService.saveEmployee(employee);
    }

}