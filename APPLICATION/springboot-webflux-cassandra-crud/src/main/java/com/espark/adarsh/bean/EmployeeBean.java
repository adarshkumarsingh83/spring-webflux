package com.espark.adarsh.bean;

import com.espark.adarsh.entity.EmployeeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBean {

    private Integer id;
    private String name;

    @JsonIgnore
    public EmployeeEntity getEntity() {
        return new EmployeeEntity(this.id, this.name);
    }
}
