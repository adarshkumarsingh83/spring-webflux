package com.espark.adarsh.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class ApplicationConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        String url ="r2dbc:oracle://localhost:1521/XE";
        log.info("Creating connection factory with URL:" + url);

        return ConnectionFactories.get(ConnectionFactoryOptions.parse(url)
                .mutate()
                .option(ConnectionFactoryOptions.USER, "SYSTEM")
                .option(ConnectionFactoryOptions.PASSWORD, System.getenv("12345"))
                .build());
    }

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        ResourceDatabasePopulator resource =
                new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
        initializer.setDatabasePopulator(resource);
        return initializer;
    }

    @Bean
    ReactiveAuditorAware<String> auditorAware() {
        return () -> Mono.just("AUDITOR");
    }
}
