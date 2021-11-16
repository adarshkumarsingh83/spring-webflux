package com.espark.adarsh;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest
public class OnErrorResumeTest {

    @Autowired
    DataService dataService;


    @Test
    public void onErrorResume_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(error -> Mono.just(4))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfArithmeticException_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        ArithmeticException.class,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfPredicatePasses_Mono() {
        dataService.getMonoInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        error -> error instanceof ArithmeticException,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


    @Test
    public void onErrorResume_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(error -> Mono.just(4))
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfArithmeticException_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        ArithmeticException.class,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }

    @Test
    public void onErrorResumeIfPredicatePasses_Flux() {
        dataService.getFluxInteger()
                .map(i -> i / 0) // will produce ArithmeticException
                .onErrorResume(
                        error -> error instanceof ArithmeticException,
                        error -> Mono.just(4)
                )
                .subscribe(num -> log.info("Number: {}", num));
    }


}
