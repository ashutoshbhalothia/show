package com.backend.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalClientService {

    private final WebClient webClient;

    @Autowired
    public ExternalClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public<T> CompletableFuture<T> executePOST(String name,String url,Class<T> clazz) {

        return webClient.post()
                .uri(url)
                .body(Mono.just(name), String.class)
                .retrieve()
                .bodyToMono(clazz)
                .toFuture();
    }

    public<U,T> Iterable<T> executePOSTgetList(U request, String url, Class<T> clazz) {

        return webClient.post()
                .uri(url)
                .body(Mono.just(request), request.getClass())
                .retrieve()
                .bodyToFlux(clazz)
                .toIterable();
    }

    public<T> CompletableFuture<T> executeGET(String url, Class<T> clazz) {

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(clazz)
                .toFuture();
    }

    public<T> Iterable<T> executeGETgetList(String url,Class<T> clazz) {

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(clazz).
                toIterable();
    }
}
