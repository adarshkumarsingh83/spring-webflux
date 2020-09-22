package com.espark.adarsh.web;

import com.espark.adarsh.bean.EventBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping(value="/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventBean> events(){
        Flux<EventBean> eventBeanFlux
                = Flux.fromStream(Stream.generate(()-> new EventBean(System.currentTimeMillis(),"server-live",new Date())));
        Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(eventBeanFlux,durationFlux).map(Tuple2::getT1);
    }
    
    
}
