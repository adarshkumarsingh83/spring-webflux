package com.espark.adarsh.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DataService {

    public Mono<Integer> getMonoInteger() {
        int min = 0;
        int max = 10;
        Random random = new Random();
        return Mono.just(random.nextInt(max - min) + min);
    }

    public Flux<Integer> getFluxInteger() {
        return Flux.fromIterable(IntStream.range(0, 10).boxed().collect(Collectors.toList()));
    }
}
