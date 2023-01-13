package com.espark.adarsh.web;

import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class EurekaServer {


    @GetMapping(value = {"/service-discovery", "/service-discovery/{applicationName}"})
    public List<Application> getClientList(@PathVariable(value = "applicationName", required = false)
                                           String applicationName) {
        PeerAwareInstanceRegistry registry
                = EurekaServerContextHolder.getInstance()
                .getServerContext()
                .getRegistry();
        Applications applications
                = registry.getApplications();
        if (StringUtils.isEmpty(applicationName)) {
            applications.getRegisteredApplications()
                    .forEach((registeredApplication) -> {
                        registeredApplication.getInstances()
                                .forEach((instance) -> {
                                    log.info(instance.getAppName() + " "
                                            + instance.getInstanceId() + " "
                                            + instance.getHostName());
                                });
                    });
            return applications.getRegisteredApplications();
        } else {
            return Arrays.asList(applications.getRegisteredApplications(applicationName));
        }
    }
}

