package com.espark.adarsh.web;

import com.espark.adarsh.entity.DataEntity;
import com.espark.adarsh.repository.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Log4j2
@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataEntityRepository dataEntityRepository;

    @GetMapping(value = "/controller/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<DataEntity> getData() {
        return dataEntityRepository.findAll();
    }

    @GetMapping(value = "/controller/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DataEntity> getDataStream() {

       /* return Flux.range(0, Integer.MAX_VALUE)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(index -> log.info("Processing Index {}", index))
                .map(index -> new DataEntity(index, "adarsh@kumar"))
                .doOnNext(dataEntity -> log.info("Data Return {}", dataEntity));*/

        return Flux.range(0, Integer.MAX_VALUE)
                .doOnNext(index -> log.info("Processing Index {}", index))
                .delayElements(Duration.ofSeconds(1))
                .map(index -> new DataEntity(index, "adarsh@kumar"))
                .doOnNext(dataEntity -> log.info("Data Return {}", dataEntity))
                .filter(dataEntity -> dataEntity != null);
    }
}
