package com.espark.adarsh.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MessageBean<T> implements Serializable {
    private Long id;
    private String event;
    private T data;
}
