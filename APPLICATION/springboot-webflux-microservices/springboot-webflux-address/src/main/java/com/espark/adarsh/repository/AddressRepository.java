package com.espark.adarsh.repository;

import com.espark.adarsh.entity.AddressEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends ReactiveMongoRepository<AddressEntity,Long> {

}

