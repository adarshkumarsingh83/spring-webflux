package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class DataInitService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ReactiveMongoOperations reactiveMongoOperations;


    @PostConstruct
    public void init() {
        Flux<Employee> employeesList = Flux.just(
                new Employee ("adarsh", "kumar", "It"),
                new Employee("radha", "singh", "IT"),
                new Employee("amit", "kumar", "Finance")
        );

        Flux<Employee> employeesListSave = employeesList.flatMap(employee -> {
            return this.employeeRepository.save(employee);
        });

        if(reactiveMongoOperations.collectionExists(Employee.class).block()){
            this.employeeRepository.findAll().subscribe(employee -> {
                log.info(employee.toString());
            });
        }
    }
}
