package com.espark.adarsh.repository;

import com.espark.adarsh.entity.DataEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEntityRepository extends R2dbcRepository<DataEntity,Integer> {

}
