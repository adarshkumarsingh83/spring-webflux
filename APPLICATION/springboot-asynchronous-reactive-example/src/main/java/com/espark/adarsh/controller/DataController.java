package com.espark.adarsh.controller;

import com.espark.adarsh.bean.DataBean;
import com.espark.adarsh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping(value = "/data-list/{limit}")
    public Flux<DataBean> getDataList(@PathVariable("limit") Integer limit) {
        return this.dataService.dataBeanList(limit);
    }

    @GetMapping(value = "/data-stream/{limit}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DataBean> getDataString(@PathVariable("limit") Integer limit) {
        return this.dataService.dataBeanStream(limit);
    }
}
