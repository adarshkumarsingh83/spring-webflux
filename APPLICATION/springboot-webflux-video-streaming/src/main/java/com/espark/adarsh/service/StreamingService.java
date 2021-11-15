package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamingService {

    private static final String FORMAT="classpath:videos/%s.mov";

    @Autowired
    ResourceLoader resourceLoader;

    public Mono<Resource> getVideoFromLibrary(String videoFileName){
        return Mono.fromSupplier(()->resourceLoader.
                getResource(String.format(FORMAT,videoFileName)))   ;
    }
}