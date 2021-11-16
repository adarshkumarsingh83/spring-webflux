package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Mono<EmployeeBean> saveEmployee(EmployeeBean employeeBean) {
        log.info("EmployeeService:saveEmployee {}", employeeBean);
        return Mono.just(employeeBean)
                .map(employeeBean1 -> employeeBean1.getEntity())
                .flatMap(employeeRepository::save)
                .map(employeeEntity -> employeeEntity.getBean());
    }

    public Mono<EmployeeBean> updateEmployee(Integer employeeId, EmployeeBean employeeBean) {
        log.info("EmployeeService:updateEmployee {} , {}", employeeId, employeeBean);
        return this.employeeRepository.findById(employeeId)
                .flatMap(employeeEntity -> Mono.just(employeeBean)
                        .map(employeeBeanMono -> employeeBeanMono.getEntity())
                        .doOnNext(employeeEntity1 -> employeeEntity1.setId(employeeId)))
                .flatMap(employeeRepository::save)
                .map(employeeEntity -> employeeEntity.getBean());
    }

    public Mono<Void> deleteEmployee(Integer employeeId) {
        log.info("EmployeeService:deleteEmployee {}", employeeId);
        return this.employeeRepository.deleteById(employeeId);
    }

    public Mono<EmployeeBean> getEmployee(Integer employeeId) {
        log.info("EmployeeService:getEmployee {}", employeeId);
        return this.employeeRepository.findById(employeeId)
                .map(employeeEntity -> employeeEntity.getBean());
    }

    public Flux<EmployeeBean> getEmployees() {
        log.info("EmployeeService:getEmployees ");
        return this.employeeRepository.findAll()
                .map(employeeEntity -> employeeEntity.getBean());
    }
}
