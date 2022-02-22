package com.espark.adarsh;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(RabbitConfigurationProp.class)
public class ApplicationMain {


	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args).close();
	}
}
