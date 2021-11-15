package com.espark.adarsh;

import com.espark.adarsh.bean.DataBean;
import com.espark.adarsh.repository.DataRepository;
import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@SpringBootTest
class SpringbootWebfluxBasicApplicationTests {

    @Autowired
    DataService dataService;

    @Autowired
    DataRepository dataRepository;

    @Test
    void contextLoads() {
    }


    @Test
    void testMonoData() {
        int index = ThreadLocalRandom.current().nextInt(0, dataRepository.getStoreSize());
        log.info("Index Generated For Data {}", index);
        Mono<DataBean> dataBeanMono = dataService.dataBeanMono(index).log();
        dataBeanMono.subscribe(System.out::println);
    }

    @Test
    void testFluxData() {
        Flux<DataBean> dataBeanFlux = this.dataService.dataBeanFlux().log();
        dataBeanFlux.subscribe(System.out::println);
    }
}
