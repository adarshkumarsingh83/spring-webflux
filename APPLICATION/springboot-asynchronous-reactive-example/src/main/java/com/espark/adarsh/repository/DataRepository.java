package com.espark.adarsh.repository;

import com.espark.adarsh.bean.DataBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class DataRepository {


    public Flux<DataBean> dataBeanList(int limit) {
        log.info("DataService::dataBeanList() limit {}", limit);
        List<DataBean> dataBeans =
                IntStream.rangeClosed(0, limit)
                        .peek(index -> log.info("processing index {}", index))
                        .peek(DataRepository::delay)
                        .mapToObj(index -> new DataBean(index, "data_" + index))
                        .collect(Collectors.toList());
        return Flux.fromIterable(dataBeans);
    }


    public Flux<DataBean> dataBeanStream(int limit) {
        log.info("DataService::dataBeanStream() limit {}", limit);

        return Flux.range(0, limit)
                .doOnNext(index -> log.info("processing index {}", index))
                .delayElements(Duration.ofSeconds(1))
                .map(index -> new DataBean(index, "data_" + index));

    }

    public static void delay(Integer i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }
    }

}
