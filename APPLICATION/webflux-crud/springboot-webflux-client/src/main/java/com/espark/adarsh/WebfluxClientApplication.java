package com.espark.adarsh;

import com.espark.adarsh.services.ServerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class WebfluxClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebfluxClientApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ServerService serverService){
		return args -> {
			serverService.serverLiveStatus();
		};
	}
}
