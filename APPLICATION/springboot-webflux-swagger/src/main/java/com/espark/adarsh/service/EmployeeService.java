package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.exception.EmployeeAlreadyExists;
import com.espark.adarsh.exception.EmployeeNotExists;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Mono<Employee> saveEmployee(Employee employee) {
        try {
            return Mono.just(this.employeeRepository.saveEmployee(employee));
        } catch (EmployeeAlreadyExists e) {
            return Mono.error(e);
        }
    }

    public Mono<Employee> updateEmployee(Integer employeeId, Employee employee) {
        try {
            return Mono.just(this.employeeRepository.updateEmployee(employeeId,employee));
        } catch (EmployeeNotExists e) {
            return Mono.error(e);
        }
    }

    public Mono<Employee> deleteEmployee(Integer employeeId) {
        try {
            return Mono.just(this.employeeRepository.deleteEmployee(employeeId));
        } catch (EmployeeNotExists e) {
            return Mono.error(e);
        }
    }

    public Mono<Employee> getEmployee(Integer employeeId) {
        try {
            Employee employee = this.employeeRepository.getEmployee(employeeId);
            return Mono.just(employee);
        } catch (EmployeeNotExists e) {
            return Mono.error(e);
        }
    }

    public Flux<Employee> getEmployees() {
        try {
            List<Employee> employeeList = this.employeeRepository.getEmployees();
            return Flux.fromIterable(employeeList);
        } catch (EmployeeNotExists e) {
            return Flux.error(e);
        }
    }
}
