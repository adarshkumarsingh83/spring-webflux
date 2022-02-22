package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class RabbitMqConfig {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    @PostConstruct
    public void init() {
        rabbitConfigurationProp.getQueues()
                .forEach((key, config) -> {
                    Exchange exchange = ExchangeBuilder.directExchange(config.getExchange())
                            .durable(true)
                            .build();
                    amqpAdmin.declareExchange(exchange);
                    Queue queue = QueueBuilder.durable(rabbitConfigurationProp.getQueueName())
                            .build();
                    amqpAdmin.declareQueue(queue);
                    Binding binding = BindingBuilder.bind(queue)
                            .to(exchange)
                            .with(config.getRoutingKey())
                            .noargs();
                    amqpAdmin.declareBinding(binding);
                });
    }

    @Bean
    public MessageListenerContainer createMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer mlc = new SimpleMessageListenerContainer(connectionFactory);
        mlc.addQueueNames(rabbitConfigurationProp.getQueueName());
        return mlc;
    }
}
