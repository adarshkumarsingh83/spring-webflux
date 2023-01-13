package com.espark.adarsh.util;


import com.espark.adarsh.bean.EmployeeRecord;
import com.espark.adarsh.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class DataLoader {

    @Autowired
    EmployeeRepository employeeRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Flux<EmployeeRecord> addressRecordFlux = Flux.range(0, 10)
                .doOnNext(index -> log.info("Processing index {}", index))
                .map(integer -> Long.parseLong(integer.toString()))
                .map(index -> new EmployeeRecord(index, "firstname_" + index, "lastName_" + index, "carrer_" + index));

        addressRecordFlux.doOnNext(employeeRecord -> log.info("Processing Employee {}", employeeRecord))
                .map(employeeRecord -> employeeRecord.employeeEntity())
                .flatMap(employeeRepository::save)
                .map(addressEntity -> addressEntity.employeeRecord())
                .doOnNext(employeeRecord -> log.info("Employee Saved {}", employeeRecord));
    }
}
