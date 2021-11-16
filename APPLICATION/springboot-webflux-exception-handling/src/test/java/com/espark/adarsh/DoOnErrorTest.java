package com.espark.adarsh;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class DoOnErrorTest {

    @Autowired
    DataService dataService;

    @Test
    public void doOnError_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(error -> log.info("Exception Caught & Processing"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        ArithmeticException.class,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        error -> error instanceof ArithmeticException,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void doOnError_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(error -> log.info("Exception Caught & Processing"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        ArithmeticException.class,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void doOnErrorIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .doOnError(
                        error -> error instanceof ArithmeticException,
                        error -> log.info("Exception Caught & Processing")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

}
