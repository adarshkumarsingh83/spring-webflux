package com.espark.adarsh.util;

import com.espark.adarsh.entity.DataEntity;
import com.espark.adarsh.repository.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Log4j2
@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final DataEntityRepository entityRepository;
    private static int index = 0;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("DataInitializer::init ");
        Flux<DataEntity> dataEntityFlux = Flux.just("adarsh", "radha", "amit", "sonu", "monu", "mummy", "papa")
                .map(name -> new DataEntity(index++, name))
                .flatMap(this.entityRepository::save);
        this.entityRepository.deleteAll()
                .thenMany(dataEntityFlux)
                .thenMany(this.entityRepository.findAll())
                .subscribe(log::info);
    }
}
