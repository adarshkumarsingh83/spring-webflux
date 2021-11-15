package com.espark.adarsh.controller;

import com.espark.adarsh.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class StreamingController {

    @Autowired
    private StreamingService service;

    @GetMapping(value = "video/{videoFileName}", produces = "video/quicktime")
    public Mono<Resource> getVideos(@PathVariable String videoFileName, @RequestHeader("Range") String range) {
        System.out.println("range in bytes() : " + range);
        return service.getVideoFromLibrary(videoFileName);
    }

}
