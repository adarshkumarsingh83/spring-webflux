package com.espark.adarsh.config;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class RabbitMqConfig {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    Mono<Connection> connectionMono;

    @Autowired
    RabbitConfigurationProp rabbitConfigurationProp;

    static final String QUEUE = "espark-queue";

    @PostConstruct
    public void init() {
        amqpAdmin.declareQueue(new Queue(QUEUE, false, false, true));
    }


    @Bean()
    Mono<Connection> connectionMono(RabbitProperties rabbitProperties) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        connectionFactory.setHost(rabbitProperties.getHost());
        connectionFactory.setPort(rabbitProperties.getPort());
        connectionFactory.setUsername(rabbitProperties.getUsername());
        connectionFactory.setPassword(rabbitProperties.getPassword());
        return Mono.fromCallable(() -> connectionFactory.newConnection(this.rabbitConfigurationProp.getConnectionName()))
                .cache();
    }

    @Bean
    Sender sender(Mono<Connection> connectionMono) {
        Sender sender = RabbitFlux.createSender(new SenderOptions().connectionMono(connectionMono));
        String queueName = this.rabbitConfigurationProp.getQueueName();
        String exchangeName = this.rabbitConfigurationProp.getQueues().get("espark-message-queue").getExchange();
        String routingKey = this.rabbitConfigurationProp.getQueues().get("espark-message-queue").getRoutingKey();
        Mono<AMQP.Exchange.DeclareOk> exchange = sender.declareExchange(
                ExchangeSpecification.exchange(exchangeName)
        );
        Mono<AMQP.Queue.DeclareOk> queue = sender.declareQueue(
                QueueSpecification.queue(queueName)
        );
        Mono<AMQP.Queue.BindOk> binding = sender.bind(
                BindingSpecification.binding().exchange(exchangeName)
                        .queue(queueName).routingKey(routingKey)
        );
        return sender;
    }

    @Bean
    Receiver receiver(Mono<Connection> connectionMono) {
        return RabbitFlux.createReceiver(new ReceiverOptions().connectionMono(connectionMono));
    }

    @Bean
    Flux<Delivery> deliveryFlux(Receiver receiver) {
        return receiver.consumeNoAck(QUEUE);
    }


  /*  @PreDestroy
    public void close() throws Exception {
        connectionMono.block().close();
    }*/

}
