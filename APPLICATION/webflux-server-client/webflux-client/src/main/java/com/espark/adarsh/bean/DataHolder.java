package com.espark.adarsh.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class DataHolder implements Serializable {

    String message;
    Object data;


    public String getData(){
        return "{'message':'"+this.message+"','data':'"+this.data+"'}";
    }
}
