package com.digitalnurture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Verifies the resilience behaviour end-to-end:
 * a request to /cb/** is routed to an unreachable backend, the CircuitBreaker
 * filter catches the failure and forwards to /fallback, so the client receives
 * the fallback response instead of an error.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ResilienceGatewayTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void unreachableBackendTriggersFallback() {
        webTestClient.get()
                .uri("/cb/anything")
                .exchange()
                .expectStatus().isEqualTo(503)
                .expectBody(String.class)
                .value(body -> org.assertj.core.api.Assertions.assertThat(body)
                        .contains("Fallback response"));
    }
}
