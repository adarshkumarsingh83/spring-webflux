package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/trigger-sse")
    public String triggerSse() {
        this.applicationService.triggerSse();
        return "started working on sse api call";
    }

    @GetMapping("/trigger-flux")
    public String triggerFlux() {
        this.applicationService.triggerFluxStream();
        return "started working on webflux stream  api call";
    }

    @GetMapping("/trigger-flux-object")
    public String triggerFluxObject() {
        this.applicationService.triggerFluxStreamObject();
        return "started working on webflux object stream  api call";
    }
}
