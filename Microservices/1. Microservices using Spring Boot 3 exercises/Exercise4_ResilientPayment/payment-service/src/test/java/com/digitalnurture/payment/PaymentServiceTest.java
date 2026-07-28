package com.digitalnurture.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The third-party client always fails, so the circuit breaker routes the call
 * to the fallback. The service must return the fallback response rather than
 * propagating the exception.
 */
@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void fallbackIsUsedWhenThirdPartyFails() {
        String result = paymentService.processPayment("ORD-1", 250.0);
        assertThat(result).contains("fallback");
    }
}
