package com.espark.adarsh;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class OnErrorMapTest {

    @Autowired
    DataService dataService;


    @Test
    public void OnErrorMap_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(error -> new RuntimeException("ApplicationCalculationException"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        ArithmeticException.class,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        error -> error instanceof ArithmeticException,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMap_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(error -> new RuntimeException("ApplicationCalculationException"))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        ArithmeticException.class,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void OnErrorMapIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorMap(
                        error -> error instanceof ArithmeticException,
                        error -> new RuntimeException("ApplicationCalculationException")
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


}
