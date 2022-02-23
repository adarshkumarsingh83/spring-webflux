package com.espark.adarsh.repository;

import com.espark.adarsh.entity.DataEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEntityRepository extends ReactiveSortingRepository<DataEntity,Integer> {

}
