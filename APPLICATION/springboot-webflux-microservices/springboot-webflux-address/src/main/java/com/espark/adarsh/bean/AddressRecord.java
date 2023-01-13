package com.espark.adarsh.bean;

import com.espark.adarsh.entity.AddressEntity;

public record AddressRecord(Long id,
                            String streetName,
                            String cityName,
                            String country) {


    public AddressRecord(AddressEntity entity) {
        this(entity.getId(), entity.getStreetName(), entity.getCityName(), entity.getCountry());
    }

    public AddressEntity addressEntity() {
        return new AddressEntity(id(), streetName(), cityName(), country());
    }
}
