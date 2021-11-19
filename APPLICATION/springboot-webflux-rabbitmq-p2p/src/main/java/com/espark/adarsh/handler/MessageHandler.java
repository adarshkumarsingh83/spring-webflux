package com.espark.adarsh.handler;

import com.espark.adarsh.service.reciver.MessageReceiverService;
import com.espark.adarsh.service.sender.MessageSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class MessageHandler {

    @Autowired
    MessageSenderService messageSenderService;

    @Autowired
    MessageReceiverService messageReceiverService;

    public Mono<ServerResponse> publishMessage(ServerRequest serverRequest) {
        log.info("MessageHandler::publishMessage");
        return serverRequest.bodyToMono(List.class)
                .map(list -> (List<String>) list)
                .flatMap(messageList -> messageSenderService.sendMessageToRabbitMq(messageList).collectList())
                .doOnNext(responseDataList -> log.info("Response After Publishing", responseDataList))
                .flatMap(messages -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(messages)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));
    }

    public Mono<ServerResponse> subscribeMessage(ServerRequest serverRequest) {
        log.info("MessageHandler::subscribeMessage");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(messageReceiverService.consumeDataFromRabbitMq(), List.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }
}
