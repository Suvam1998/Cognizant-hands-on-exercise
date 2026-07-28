package com.digitalnurture.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for the in-memory rate limiter: the first N requests pass, the
 * (N+1)th from the same client is rejected with 429.
 */
class RateLimitingFilterTest {

    @Test
    void rejectsRequestsOverTheLimit() {
        RateLimitingFilter filter = new RateLimitingFilter();
        ReflectionTestUtils.setField(filter, "maxRequests", 3);
        ReflectionTestUtils.setField(filter, "windowMillis", 10_000L);

        // A downstream chain that just completes successfully.
        GatewayFilterChain passThrough = exchange -> Mono.empty();

        // First 3 requests from the same IP should pass through (200/committed by chain).
        for (int i = 0; i < 3; i++) {
            MockServerWebExchange exchange = exchangeFromIp("10.0.0.1");
            filter.filter(exchange, passThrough).block();
            assertThat(exchange.getResponse().getStatusCode())
                    .as("request %d should be allowed", i + 1)
                    .isNotEqualTo(HttpStatus.TOO_MANY_REQUESTS);
        }

        // The 4th request should be throttled.
        MockServerWebExchange throttled = exchangeFromIp("10.0.0.1");
        filter.filter(throttled, passThrough).block();
        assertThat(throttled.getResponse().getStatusCode())
                .isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
    }

    private MockServerWebExchange exchangeFromIp(String ip) {
        MockServerHttpRequest request = MockServerHttpRequest
                .get("/api/customers/1")
                .remoteAddress(new InetSocketAddress(ip, 12345))
                .build();
        return MockServerWebExchange.from(request);
    }
}
