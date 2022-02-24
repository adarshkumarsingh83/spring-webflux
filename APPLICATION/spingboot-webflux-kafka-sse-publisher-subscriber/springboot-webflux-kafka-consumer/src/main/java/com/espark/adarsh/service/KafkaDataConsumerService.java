package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;

@Slf4j
@Service
public class KafkaDataConsumerService {

    @Autowired
    KafkaReceiver<String,String> kafkaReceiver;

    public Flux<String> getStreamData(){
        Flux<ReceiverRecord<String, String>> kafkaFlux = kafkaReceiver.receive();
        return kafkaFlux.checkpoint("DataConsumerService Starting Consuming Data From Kafka")
                .log()
                .doOnNext(r -> r.receiverOffset().acknowledge())
                .map(ReceiverRecord::value)
                .checkpoint("DataConsumerService has Consumed Data From Kafka");
    }
}
