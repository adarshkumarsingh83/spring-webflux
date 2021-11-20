package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootWeblfuxMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWeblfuxMongoApplication.class, args);
	}

}
