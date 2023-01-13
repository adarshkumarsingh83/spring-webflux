package com.espark.adarsh.entity;

import com.espark.adarsh.bean.AddressRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {
    @Id
    private Long id;
    private String streetName;
    private String cityName;
    private String country;

    public AddressEntity(AddressRecord addressRecord) {
        this.id = addressRecord.id();
        this.streetName = addressRecord.streetName();
        this.cityName = addressRecord.cityName();
        this.country = addressRecord.country();
    }

    public AddressRecord addressRecord() {
        return new AddressRecord(id, streetName, cityName, country);
    }
}
