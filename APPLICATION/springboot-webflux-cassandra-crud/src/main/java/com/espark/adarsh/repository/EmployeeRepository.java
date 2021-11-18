package com.espark.adarsh.repository;

import com.espark.adarsh.entity.EmployeeEntity;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends ReactiveCassandraRepository<EmployeeEntity,Integer> {

}
