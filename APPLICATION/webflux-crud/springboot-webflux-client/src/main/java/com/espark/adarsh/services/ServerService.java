package com.espark.adarsh.services;

import com.espark.adarsh.bean.EventBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServerService {

    @Autowired
    ServerIntegrationService serverIntegrationService;

    public void serverLiveStatus() {
        serverIntegrationService.serverLiveStatus();
       log.info("ServerService.serverLiveStatus() ====> event");
    }
}
