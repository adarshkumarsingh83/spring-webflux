package com.espark.adarsh.repository;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.exception.EmployeeAlreadyExists;
import com.espark.adarsh.exception.EmployeeNotExists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class EmployeeRepository {

    static final Map<Integer, Employee> store = new HashMap<>();

    @PostConstruct
    public void innit() {

        IntStream.rangeClosed(0, 10)
                .boxed()
                .forEach(id -> {
                    store.put(id, new Employee(id, "employee_" + id));
                });
        log.info("EmployeeRepository::ini() store {}", store);
    }

    public Employee saveEmployee(Employee employee) throws EmployeeAlreadyExists {
        if (store.containsKey(employee.getId())) {
            throw new EmployeeAlreadyExists("Employee Already Exist with Id " + employee.getId());
        } else {
            store.put(employee.getId(), employee);
        }
        return employee;
    }

    public Employee updateEmployee(Integer employeeId, Employee employee) throws EmployeeNotExists {
        if (!store.containsKey(employeeId)) {
            throw new EmployeeNotExists("Employee Not Exist with Id " + employeeId);
        } else {
            store.put(employee.getId(), employee);
        }
        return employee;
    }

    public Employee deleteEmployee(Integer employeeId) throws EmployeeNotExists {
        if (!store.containsKey(employeeId)) {
            throw new EmployeeNotExists("Employee Not Exist with Id " + employeeId);
        } else {
            return store.remove(employeeId);
        }
    }

    public Employee getEmployee(Integer employeeId) throws EmployeeNotExists {
        if (!store.containsKey(employeeId)) {
            throw new EmployeeNotExists("Employee Not Exist with Id " + employeeId);
        } else {
            return store.get(employeeId);
        }
    }

    public List<Employee> getEmployees() throws EmployeeNotExists {
        if (store.isEmpty()) {
            throw new EmployeeNotExists("Employee Not Exists ");
        } else {
            return store.entrySet()
                    .stream()
                    .map(entrySet -> entrySet.getValue())
                    .collect(Collectors.toList());
        }
    }
}
