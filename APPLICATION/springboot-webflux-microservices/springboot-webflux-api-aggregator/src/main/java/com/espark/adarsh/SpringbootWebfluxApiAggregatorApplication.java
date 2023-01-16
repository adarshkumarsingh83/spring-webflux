package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootWebfluxApiAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxApiAggregatorApplication.class, args);
	}

}
