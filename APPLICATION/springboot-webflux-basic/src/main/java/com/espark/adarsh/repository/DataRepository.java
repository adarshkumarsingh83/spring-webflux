package com.espark.adarsh.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Repository
public class DataRepository {

    static final List<String> dataStore = Arrays.asList("adarsh", "radha", "sonu", "monu");

    public String getName(int index) {
        log.info("DataRepository::getName() index {}", index);
        return dataStore.get(index);
    }

    public int getStoreSize() {
        log.info("DataRepository::getStoreSize() ");
        return dataStore.size();
    }

}
