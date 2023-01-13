package com.espark.adarsh.service;

import com.espark.adarsh.bean.AddressRecord;
import com.espark.adarsh.entity.AddressEntity;
import com.espark.adarsh.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public Mono<AddressRecord> saveAddress(AddressRecord addressRecordObject) {
        log.info("AddressService::saveAddress {}", addressRecordObject);
        return Mono.just(addressRecordObject)
                .map(addressRecord -> addressRecord.addressEntity())
                .flatMap(repository::save)
                .map(addressEntity -> addressEntity.addressRecord());
    }

    public Mono<AddressRecord> updateAddress(Long addressId, AddressRecord addressRecordObject) {
        log.info("AddressService::updateAddress {} {}", addressId, addressRecordObject);
        return this.repository.findById(addressId)
                .flatMap(addressEntityFromDB -> Mono.just(addressRecordObject)
                        .map(addressRecord -> addressRecord.addressEntity())
                        .doOnNext(addressEntity -> addressEntity.setId(addressEntityFromDB.getId()))
                        .flatMap(repository::save)
                        .map(AddressEntity::addressRecord)
                );
    }

    public Mono<AddressRecord> deleteAddress(Long addressId) {
        log.info("AddressService::deleteAddress  {}", addressId);
        Mono<AddressRecord> addressRecordMono =
                this.repository.findById(addressId)
                        .map(addressEntity -> addressEntity.addressRecord());
        this.repository.deleteById(addressId);
        return addressRecordMono;
    }

    public Mono<AddressRecord> getAddress(Long addressId) {
        log.info("AddressService::getAddress  {}", addressId);
        Mono<AddressRecord> addressRecordMono =
                this.repository.findById(addressId)
                        .map(addressEntity -> addressEntity.addressRecord());
        return addressRecordMono;
    }

    public Flux<AddressRecord> getAddresses() {
        log.info("AddressService::getAddresses  ");
        return this.repository.findAll()
                .map(addressEntity -> addressEntity.addressRecord());
    }
}
