package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringbootSseClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSseClientApplication.class, args);
	}

}
