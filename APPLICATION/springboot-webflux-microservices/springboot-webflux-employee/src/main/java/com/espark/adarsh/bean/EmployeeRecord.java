package com.espark.adarsh.bean;

import com.espark.adarsh.entity.EmployeeEntity;

public record EmployeeRecord(Long id,
                             String firstName,
                             String lastName,
                             String career) {


    public EmployeeRecord(EmployeeEntity entity) {
        this(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getCareer());
    }

    public EmployeeEntity employeeEntity() {
        return new EmployeeEntity(id(), firstName(), lastName(), career());
    }
}
