package com.espark.adarsh.repository;

import com.espark.adarsh.entity.DataEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEntityRepository extends ReactiveCrudRepository<DataEntity,String> {

}
