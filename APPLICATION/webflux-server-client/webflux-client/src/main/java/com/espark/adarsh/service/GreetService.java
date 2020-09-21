package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Async
public class GreetService {

    @Autowired
    GreetingWebClient greetingWebClient;


    public void greet(String type, String name){
        log.info("GreetService greet()");
        String response = this.greetingWebClient.getResult(type, name);
        log.info("=====>>>>>> "+response);
    }
}
