package com.espark.adarsh.web;

import com.espark.adarsh.services.ServerIntegrationService;
import com.espark.adarsh.services.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {
    
    @Autowired
    ServerService serverService;
    
    @GetMapping(value="/event")
    public void monitorServer(){
        this.serverService.serverLiveStatus();
    }
    
}
