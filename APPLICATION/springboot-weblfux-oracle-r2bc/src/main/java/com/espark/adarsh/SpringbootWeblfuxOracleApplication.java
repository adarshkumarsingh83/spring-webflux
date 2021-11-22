package com.espark.adarsh;

import com.espark.adarsh.config.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableR2dbcAuditing
@EnableR2dbcRepositories
@Import({ApplicationConfiguration.class})
public class SpringbootWeblfuxOracleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeblfuxOracleApplication.class, args);
    }

}
