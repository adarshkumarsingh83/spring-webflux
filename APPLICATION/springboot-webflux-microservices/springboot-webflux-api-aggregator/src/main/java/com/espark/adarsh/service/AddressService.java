package com.espark.adarsh.service;

import com.espark.adarsh.bean.AddressRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AddressService {

    @Autowired
    @Qualifier("addressWebClient")
    WebClient addressWebClient;


    //@CircuitBreaker(name = "getAddress", fallbackMethod = getAddressDefault)
    public Mono<AddressRecord> getAddress(Long id) {
        return addressWebClient.get()
                .uri("/address/" + id)
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())
                .bodyToMono(AddressRecord.class);
    }

    public Mono<Response<Boolean>> getAddress(Exception exception) {
        return Mono.just(ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR))
                .doOnNext(result -> log.warn("fallback executed"));
    }

    public Flux<AddressRecord> getAddress() {
        return addressWebClient.get()
                .uri("/address")
                .retrieve()
                .onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
                        clientResponse -> Mono.empty())
                .bodyToFlux(AddressRecord.class);
    }

    public Mono<AddressRecord> saveAddress(AddressRecord addressRecord) {
        return addressWebClient.post()
                .uri("/address")
                .body(Mono.just(addressRecord), AddressRecord.class)
                .retrieve()
                .bodyToMono(AddressRecord.class);
    }

    public Mono<AddressRecord> updateAddress(Long id, AddressRecord addressRecord) {
        return addressWebClient.put()
                .uri("/address/" + id)
                .body(Mono.just(addressRecord), AddressRecord.class)
                .retrieve()
                .bodyToMono(AddressRecord.class);
    }

    public Mono<AddressRecord> delete(Long id) {
        return addressWebClient.delete()
                .uri("/address/" + id)
                .retrieve()
                .bodyToMono(AddressRecord.class);
    }
}
