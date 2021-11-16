package com.espark.adarsh;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class OnErrorReturnTest {

    @Autowired
    DataService dataService;


    @Test
    public void onErrorReturnDirectly_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(ArithmeticException.class, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(error -> error instanceof ArithmeticException, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnDirectly_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfArithmeticException_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(ArithmeticException.class, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorReturnIfPredicatePasses_Flex() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorReturn(error -> error instanceof ArithmeticException, 4)
                .subscribe(num -> log.info("Number: {}", num));
    }

}
