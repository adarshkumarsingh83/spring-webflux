package com.espark.adarsh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventBean {
    Long id;
    String eventType;
    Date eventDate;
}
