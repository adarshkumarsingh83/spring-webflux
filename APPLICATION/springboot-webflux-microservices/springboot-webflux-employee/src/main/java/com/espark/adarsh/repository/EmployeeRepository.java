package com.espark.adarsh.repository;

import com.espark.adarsh.entity.EmployeeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<EmployeeEntity,Long> {

}

