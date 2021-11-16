package com.espark.adarsh.entity;

import com.espark.adarsh.bean.EmployeeBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class EmployeeEntity {

    @Id
    private Integer id;
    private String name;

    @JsonIgnore
    public EmployeeBean getBean() {
        return new EmployeeBean(this.id, this.name);
    }
}
