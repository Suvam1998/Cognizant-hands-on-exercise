package com.digitalnurture.payment;

import org.springframework.stereotype.Component;

/**
 * Simulates a slow / unreliable third-party payment API. In this sample it
 * always fails so the circuit breaker and fallback can be demonstrated.
 */
@Component
public class ThirdPartyPaymentClient {

    public String charge(String orderId, double amount) {
        // Simulate the external API being down / timing out.
        throw new RuntimeException("Third-party payment API is unavailable");
    }
}
