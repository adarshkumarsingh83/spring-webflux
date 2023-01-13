package com.espark.adarsh.util;


import com.espark.adarsh.bean.AddressRecord;
import com.espark.adarsh.repository.AddressRepository;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class DataLoader {

    @Autowired
    AddressRepository addressRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Flux<AddressRecord> addressRecordFlux = Flux.range(0, 10)
                .doOnNext(index -> log.info("Processing index {}", index))
                .map(integer -> Long.parseLong(integer.toString()))
                .map(index -> new AddressRecord(index, "Street" + index, "City_" + index, "Country_" + index));

        addressRecordFlux.doOnNext(addressRecord -> log.info("Processing Address {}", addressRecord))
                .map(addressRecord -> addressRecord.addressEntity())
                .flatMap(addressRepository::save)
                .map(addressEntity -> addressEntity.addressRecord())
                .doOnNext(addressRecord -> log.info("Address Saved {}", addressRecord));
    }
}
