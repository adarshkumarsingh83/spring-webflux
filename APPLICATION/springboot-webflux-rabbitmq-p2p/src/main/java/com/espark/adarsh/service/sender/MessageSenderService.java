package com.espark.adarsh.service.sender;

import com.espark.adarsh.config.RabbitConfigurationProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageSenderService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public Mono<List<String>> sendMessageToRabbitMq(RabbitConfigurationProp.Configuration configuration, List<String> message) {
        String messageData = message.stream().collect(Collectors.joining(","));
        return Mono.fromCallable(() -> {
            amqpTemplate.convertAndSend(configuration.getExchange(), configuration.getRoutingKey(), messageData);
            return Arrays.asList("operation successful");
        });
    }

}
