package com.espark.adarsh.web;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/{employeeId}")
    public void getById(@PathVariable("employeeId") Long employeeId) {
        this.employeeService.getById(employeeId);
    }

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee) {
        this.employeeService.saveEmployee(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
        this.employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        this.employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employees")
    public void getAllEmployee() {
        this.employeeService.getAllEmployee();
    }
}
