package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationMainTests {

	@LocalServerPort
	private int port;



	@Test
	void contextLoads() {
	}

	@Test
	public void serverSideEventTest() {
		 WebTestClient client = WebTestClient.bindToServer()
				.baseUrl("http://localhost:"+port)
				.build();
		Executable sseStreamingCall = () -> client.get()
				.uri("/router/data-stream-sse")
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
				.expectBody(String.class);

		Assertions.assertThrows(IllegalStateException.class, sseStreamingCall, "Expected test to timeout and throw IllegalStateException, but it didn't");

	}


	@Test
	public void webFluxStream() {
		WebTestClient client = WebTestClient.bindToServer()
				.baseUrl("http://localhost:"+port)
				.build();
		Executable sseStreamingCall = () -> client.get()
				.uri("/router/data-stream-flux")
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
				.expectBody(String.class);

		Assertions.assertThrows(IllegalStateException.class, sseStreamingCall, "Expected test to timeout and throw IllegalStateException, but it didn't");
	}

	@Test
	public void webFluxStreamObject() {
		WebTestClient client = WebTestClient.bindToServer()
				.baseUrl("http://localhost:"+port)
				.build();
		Executable sseStreamingCall = () -> client.get()
				.uri("/router/data-stream-flux-object")
				.exchange()
				.expectStatus()
				.isOk()
				.expectHeader()
				.contentTypeCompatibleWith(MediaType.TEXT_EVENT_STREAM)
				.expectBody(String.class);

		Assertions.assertThrows(IllegalStateException.class, sseStreamingCall, "Expected test to timeout and throw IllegalStateException, but it didn't");
	}

}


