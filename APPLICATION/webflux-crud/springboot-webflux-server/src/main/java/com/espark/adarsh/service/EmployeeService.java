package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Mono<Employee> getById(Long id) {
        return this.employeeRepository.findById(id);
    }

    public Mono<Employee> saveEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public  Mono<Employee> updateEmployee(Long employeeId, Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Mono<Employee> deleteEmployee(Long employeeId) {
        Mono<Employee> employeeMono = this.employeeRepository.findById(employeeId);
        this.employeeRepository.deleteById(employeeId);
        return employeeMono;
    }

    public Flux<Employee> getAllEmployee() {
        return this.employeeRepository.findAll();
    }
}