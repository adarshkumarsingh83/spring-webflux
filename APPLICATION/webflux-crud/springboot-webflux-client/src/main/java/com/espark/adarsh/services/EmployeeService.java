package com.espark.adarsh.services;

import com.espark.adarsh.bean.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Async
@Service
public class EmployeeService {

    @Autowired
    EmployeeIntegrationService employeeIntegrationService;

    public void getById(Long employeeId) {
        Employee employee = this.employeeIntegrationService.getById(employeeId);
        log.info("EmployeeService getById {}", employee.toString());
    }

    public void saveEmployee(Employee employee) {
        Employee employeeSaved = this.employeeIntegrationService.saveEmployee(employee);
        log.info("EmployeeService saveEmployee {}", employeeSaved.toString());
    }

    public void updateEmployee(Long employeeId, Employee employee) {
        Employee employeeUpdated = this.employeeIntegrationService.updateEmployee(employeeId, employee);
        log.info("EmployeeService updateEmployee {}", employeeUpdated.toString());
    }

    public void deleteEmployee(Long employeeId) {
        Employee employeeDeleted = this.employeeIntegrationService.deleteEmployee(employeeId);
        log.info("EmployeeService deleteEmployee {}", employeeDeleted.toString());
    }

    public void getAllEmployee() {
        Flux<Employee> employeeFlux = this.employeeIntegrationService.getAllEmployee();
        Iterable<Employee> employeeIterable = employeeFlux.toIterable();
        employeeIterable.forEach(employee -> {
            log.info("EmployeeService getAllEmployee {}", employee);
        });
    }
}
