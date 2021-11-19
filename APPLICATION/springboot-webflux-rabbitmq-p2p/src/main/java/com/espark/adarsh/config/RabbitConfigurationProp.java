package com.espark.adarsh.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties("espark")
public class RabbitConfigurationProp {

    private String queueName;

    private String connectionName;

    private Map<String, Configuration> queues = new HashMap<>();

    private Map<String, Configuration> topics = new HashMap<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Configuration {
        private String exchange;
        private String routingKey;
    }
}
