package com.espark.adarsh.web;

import com.espark.adarsh.service.GreetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
public class GreetController {

    @Autowired
    GreetService greetService;

    @GetMapping(value = "/greet-admin/{name}")
    public void greetAdmin(@PathVariable("name") String name) {
        log.info("GreetController greet()");
        this.greetService.greet("admin", name);
    }

    @GetMapping(value = "/greet-user/{name}")
    public void greetUser(@PathVariable("name") String name) {
        log.info("GreetController greetUser()");
        this.greetService.greet("user", name);
    }

    @GetMapping(value = "/greet-guest/{name}")
    public void greetGuest(@PathVariable("name") String name) {
        log.info("GreetController greetUser()");
        this.greetService.greet("guest", name);
    }


}
