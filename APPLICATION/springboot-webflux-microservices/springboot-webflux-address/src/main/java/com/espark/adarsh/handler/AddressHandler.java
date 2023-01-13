package com.espark.adarsh.handler;

import com.espark.adarsh.bean.AddressRecord;
import com.espark.adarsh.entity.AddressEntity;
import com.espark.adarsh.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AddressHandler {

    @Autowired
    AddressService addressService;

    public Mono<ServerResponse> saveAddress(ServerRequest serverRequest) {
        log.info("AddressHandler::saveAddress ");
        return serverRequest.bodyToMono(AddressRecord.class)
                .flatMap(addressRecord -> addressService.saveAddress(addressRecord))
                .flatMap(addressRecordMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(addressRecordMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));

    }

    public Mono<ServerResponse> updateAddress(ServerRequest serverRequest) {
        Long addressId = Long.valueOf(serverRequest.pathVariable("addressId"));
        log.info("AddressHandler::updateAddress {}", addressId);
        return serverRequest.bodyToMono(AddressRecord.class)
                .flatMap(addressRecord -> addressService.updateAddress(addressId, addressRecord))
                .flatMap(addressRecordMono -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(addressRecordMono)
                        .onErrorResume(e -> ServerResponse.badRequest().build()));

    }

    public Mono<ServerResponse> deleteAddress(ServerRequest serverRequest) {
        Long addressId = Long.valueOf(serverRequest.pathVariable("addressId"));
        log.info("AddressHandler::deleteAddress  {}", addressId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressService.deleteAddress(addressId), AddressRecord.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getAddress(ServerRequest serverRequest) {
        Long addressId = Long.valueOf(serverRequest.pathVariable("addressId"));
        log.info("AddressHandler::getAddress  {}", addressId);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressService.getAddress(addressId), AddressRecord.class)
                .onErrorResume(e -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getAddresses(ServerRequest serverRequest) {
        log.info("AddressHandler::getAddresses  ");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(addressService.getAddresses(), AddressRecord.class)
                .onErrorResume(e -> ServerResponse.status(500).build());
    }
}
