package com.backend.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ExternalClientService {

    private final WebClient webClient;

    @Autowired
    public ExternalClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public<T> Mono<T> executePOST(String name,String url,Class<T> clazz) {

        return webClient.post()
                .uri(url)
                .body(Mono.just(name), String.class)
                .retrieve()
                .bodyToMono(clazz);
    }

    public<U,T> Flux<T> executePOSTgetList(U request, String url, Class<T> clazz) {

        return webClient.post()
                .uri(url)
                .body(Mono.just(request), request.getClass())
                .retrieve()
                .bodyToFlux(clazz);
    }

    public<T> Mono<T> executeGET(String url,Class<T> clazz) {

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(clazz);
    }

    public<T> Flux<T> executeGETgetList(String url,Class<T> clazz) {

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(clazz);
    }
}
