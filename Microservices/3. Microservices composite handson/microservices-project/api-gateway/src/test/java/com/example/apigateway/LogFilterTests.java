package com.example.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class LogFilterTests {

    @Test
    void filterLogsAndDelegates() {
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
