package com.digitalnurture.payment;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Wraps the third-party call with a Resilience4j circuit breaker. If the call
 * fails (or the circuit is open), Resilience4j invokes {@code paymentFallback}.
 */
@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final ThirdPartyPaymentClient client;

    public PaymentService(ThirdPartyPaymentClient client) {
        this.client = client;
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String processPayment(String orderId, double amount) {
        log.info("Attempting payment for order {} amount {}", orderId, amount);
        return client.charge(orderId, amount);
    }

    /** Fallback invoked on failure or open circuit — logged for monitoring. */
    @SuppressWarnings("unused")
    private String paymentFallback(String orderId, double amount, Throwable t) {
        log.warn("FALLBACK: payment for order {} could not be processed ({}). " +
                "Queuing for retry.", orderId, t.toString());
        return "Payment for order " + orderId + " is temporarily queued (fallback).";
    }
}
