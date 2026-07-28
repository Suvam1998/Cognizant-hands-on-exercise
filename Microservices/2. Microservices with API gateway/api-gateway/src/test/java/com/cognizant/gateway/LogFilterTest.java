package com.cognizant.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test: the LogFilter passes the request on to the next filter in the chain.
 */
class LogFilterTest {

    @Test
    void filterDelegatesToChain() {
        LogFilter filter = new LogFilter();
        AtomicBoolean chainCalled = new AtomicBoolean(false);
        GatewayFilterChain chain = exchange -> {
            chainCalled.set(true);
            return Mono.empty();
        };

        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/greet-service/greet").build());

        filter.filter(exchange, chain).block();

        assertThat(chainCalled).isTrue();
    }
}
