package com.espark.adarsh;

import com.espark.adarsh.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableScheduling
@SpringBootApplication
@EnableR2dbcAuditing
@EnableR2dbcRepositories
@Import({ApplicationConfiguration.class})
public class SpringbootWeblfuxMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeblfuxMysqlApplication.class, args);
    }

}
