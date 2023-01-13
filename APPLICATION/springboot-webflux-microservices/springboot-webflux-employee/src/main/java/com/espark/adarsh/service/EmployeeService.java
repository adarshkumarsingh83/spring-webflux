package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeRecord;
import com.espark.adarsh.entity.EmployeeEntity;
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
    EmployeeRepository repository;

    public Mono<EmployeeRecord> saveEmployee(EmployeeRecord employeeRecordObject) {
        log.info("EmployeeService::saveEmployee {}", employeeRecordObject);
        return Mono.just(employeeRecordObject)
                .map(employeeRecord -> employeeRecord.employeeEntity())
                .flatMap(repository::save)
                .map(employeeEntity -> employeeEntity.employeeRecord());
    }

    public Mono<EmployeeRecord> updateEmployee(Long employeeId, EmployeeRecord employeeRecordObject) {
        log.info("EmployeeService::updateEmployee {} {}", employeeId, employeeRecordObject);
        return this.repository.findById(employeeId)
                .flatMap(employeeEntityFromDB -> Mono.just(employeeRecordObject)
                        .map(employeeRecord -> employeeRecord.employeeEntity())
                        .doOnNext(employeeEntity -> employeeEntity.setId(employeeEntityFromDB.getId()))
                        .flatMap(repository::save)
                        .map(EmployeeEntity::employeeRecord)
                );
    }

    public Mono<EmployeeRecord> deleteEmployee(Long employeeId) {
        log.info("EmployeeService::deleteEmployee  {}", employeeId);
        Mono<EmployeeRecord> addressRecordMono =
                this.repository.findById(employeeId)
                        .map(employeeEntity -> employeeEntity.employeeRecord());
        this.repository.deleteById(employeeId);
        return addressRecordMono;
    }

    public Mono<EmployeeRecord> getEmployee(Long employeeId) {
        log.info("EmployeeService::getEmployee  {}", employeeId);
        Mono<EmployeeRecord> addressRecordMono =
                this.repository.findById(employeeId)
                        .map(employeeEntity -> employeeEntity.employeeRecord());
        return addressRecordMono;
    }

    public Flux<EmployeeRecord> getEmployees() {
        log.info("EmployeeService::getEmployees  ");
        return this.repository.findAll()
                .map(employeeEntity -> employeeEntity.employeeRecord());
    }
}
