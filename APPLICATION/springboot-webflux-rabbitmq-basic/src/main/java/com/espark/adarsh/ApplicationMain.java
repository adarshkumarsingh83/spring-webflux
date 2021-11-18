package com.espark.adarsh;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@SpringBootApplication
public class ApplicationMain {

	static final String QUEUE = "reactor.rabbitmq.spring.boot";

	@Autowired
	AmqpAdmin amqpAdmin;


	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args).close();
	}

	@Component
	static class Runner implements CommandLineRunner {

		final Sender sender;
		final Flux<Delivery> deliveryFlux;
		final AtomicBoolean latchCompleted = new AtomicBoolean(false);

		Runner(Sender sender, Flux<Delivery> deliveryFlux) {
			this.sender = sender;
			this.deliveryFlux = deliveryFlux;
		}

		@Override
		public void run(String... args) throws Exception {
			int messageCount = 10;
			CountDownLatch latch = new CountDownLatch(messageCount);
			deliveryFlux.subscribe(m -> {
				log.info("Received message {}", new String(m.getBody()));
				latch.countDown();
			});
			log.info("Sending messages...");
			sender.send(Flux.range(1, messageCount).map(i -> new OutboundMessage("", QUEUE, ("Message_" + i).getBytes())))
					.subscribe();
			latchCompleted.set(latch.await(5, TimeUnit.SECONDS));
		}

	}

}
