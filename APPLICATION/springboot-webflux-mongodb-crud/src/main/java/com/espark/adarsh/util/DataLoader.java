package com.espark.adarsh.util;

import com.espark.adarsh.bean.EmployeeBean;
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

        Flux<EmployeeBean> employeeEntityFlux = Flux.range(0, 10)
                .doOnNext(index -> log.info("Processing index {}", index))
                .map(index -> new EmployeeBean(index, "Employee_" + index));

        employeeEntityFlux
                .doOnNext(EmployeeBean -> log.info("Processing Employee {}", EmployeeBean))
                .map(employeeBean -> employeeBean.getEntity())
                .flatMap(employeeRepository::save)
                .map(employeeEntity -> employeeEntity.getBean())
                .doOnNext(employeeBean -> log.info("Employee Bean {}", employeeBean));

       /* IntStream.rangeClosed(0,10)
                .peek(index -> log.info("Processing index {}", index))
                .mapToObj(index -> new EmployeeEntity(index,"Employee_"+index))
                .peek(employeeEntity -> log.info("Processing Employee {}", employeeEntity))
                .forEach(employeeRepository::save);*/
    }
}
