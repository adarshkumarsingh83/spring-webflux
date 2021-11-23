package com.espark.adarsh.util;

import com.espark.adarsh.entity.DataEntity;
import com.espark.adarsh.repository.DataEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@Log4j2
@Component
@RequiredArgsConstructor
public class DataSeedService {


    private final DataEntityRepository dataEntityRepository;
    private static int index = 0;

    @Scheduled(fixedDelay = 1000)
    public void dataSeeder() {
        Flux.just("adarsh@kumar:" + Instant.now())
                .map(name -> new DataEntity(index++, name))
                .flatMap(this.dataEntityRepository::save)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(log::info);
    }

}
