package com.espark.adarsh.web;

import com.espark.adarsh.entity.DataEntity;
import com.espark.adarsh.repository.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataEntityRepository dataEntityRepository;

    @GetMapping(value = "/controller/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<DataEntity> getDataStream() {
        return dataEntityRepository.findAll();
    }
}
