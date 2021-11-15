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

    public Flux<DataBean> dataBeanList(int limit) {
        log.info("DataService::dataBeanList() limit {}", limit);
        return dataRepository.dataBeanList(limit);
    }

    public Flux<DataBean> dataBeanStream(int limit) {
        log.info("DataService::dataBeanStream() limit {}", limit);
        return dataRepository.dataBeanStream(limit);
    }
}
