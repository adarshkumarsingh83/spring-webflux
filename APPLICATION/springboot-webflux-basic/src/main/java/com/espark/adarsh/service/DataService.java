package com.espark.adarsh.service;

import com.espark.adarsh.bean.DataBean;
import com.espark.adarsh.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public Mono<DataBean> dataBeanMono(int index) {
        log.info("DataService::dataBeanMono() index {}", index);
        return Mono.just(new DataBean(dataRepository.getName(index)));
    }

    public Flux<DataBean> dataBeanFlux() {
        log.info("DataService::dataBeanFlux()");
        /*List<DataBean> dataBeans =
                IntStream.rangeClosed(0, dataRepository.getStoreSize() - 1)
                        .peek(index -> log.info("processing index {}", index))
                        .mapToObj(index -> new DataBean(dataRepository.getName(index)))
                        .collect(Collectors.toList());
        return Flux.fromIterable(dataBeans);*/

        return Flux.range(0, dataRepository.getStoreSize() - 1)
                .doOnNext(index -> log.info("processing index {}", index))
                .map(index -> new DataBean(dataRepository.getName(index)));

    }
}
