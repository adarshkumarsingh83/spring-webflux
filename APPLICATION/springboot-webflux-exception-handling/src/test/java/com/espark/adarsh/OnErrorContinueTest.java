package com.espark.adarsh;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class OnErrorContinueTest {

    @Autowired
    DataService dataService;


    @Test
    public void onErrorContinue_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue((error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        ArithmeticException.class,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        error -> error instanceof ArithmeticException,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void onErrorContinue_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue((error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        ArithmeticException.class,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorContinueIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorContinue(
                        error -> error instanceof ArithmeticException,
                        (error, obj) -> log.info("Exception :[{}], DataObject :[{}]", error, obj)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

}
