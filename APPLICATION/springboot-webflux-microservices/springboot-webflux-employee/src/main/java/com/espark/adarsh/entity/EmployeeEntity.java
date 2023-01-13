package com.espark.adarsh.entity;

import com.espark.adarsh.bean.EmployeeRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String career;

    public EmployeeEntity(EmployeeRecord employeeRecord) {
        this.id = employeeRecord.id();
        this.firstName = employeeRecord.firstName();
        this.lastName = employeeRecord.lastName();
        this.career = employeeRecord.career();
    }

    public EmployeeRecord employeeRecord() {
        return new EmployeeRecord(id, firstName, lastName, career);
    }
}
