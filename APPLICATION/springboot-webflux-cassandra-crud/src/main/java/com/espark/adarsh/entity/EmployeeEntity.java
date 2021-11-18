package com.espark.adarsh.entity;

import com.espark.adarsh.bean.EmployeeBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("employee")
public class EmployeeEntity {

    @PrimaryKey
    private Integer id;
    private String name;

    @JsonIgnore
    public EmployeeBean getBean() {
        return new EmployeeBean(this.id, this.name);
    }
}
