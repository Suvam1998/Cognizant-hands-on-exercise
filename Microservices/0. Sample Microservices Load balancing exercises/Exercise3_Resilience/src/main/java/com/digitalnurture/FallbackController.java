package com.digitalnurture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Exercise 3 — the endpoint the CircuitBreaker filter forwards to when the
 * downstream call fails or times out (fallbackUri=forward:/fallback).
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Mono<ResponseEntity<String>> fallback() {
        return Mono.just(ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Fallback response: the service is currently unavailable"));
    }
}
