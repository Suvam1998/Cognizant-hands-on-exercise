package com.digitalnurture.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple in-memory, fixed-window rate limiter applied to every request.
 *
 * This keeps the sample self-contained (no Redis). For production, prefer the
 * built-in RequestRateLimiter filter backed by Redis
 * (spring-cloud-starter-gateway + spring-boot-starter-data-redis-reactive),
 * which works correctly across multiple gateway instances.
 */
@Component
public class RateLimitingFilter implements GlobalFilter, Ordered {

    @Value("${gateway.rate-limit.max-requests:5}")
    private int maxRequests;

    @Value("${gateway.rate-limit.window-millis:10000}")
    private long windowMillis;

    private final ConcurrentHashMap<String, Window> windows = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String clientKey = exchange.getRequest().getRemoteAddress() != null
                ? exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
                : "unknown";

        if (!allow(clientKey)) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean allow(String key) {
        long now = System.currentTimeMillis();
        Window window = windows.compute(key, (k, existing) -> {
            if (existing == null || now - existing.start >= windowMillis) {
                return new Window(now);
            }
            return existing;
        });
        return window.count.incrementAndGet() <= maxRequests;
    }

    @Override
    public int getOrder() {
        // Run before routing so throttled requests never reach the backend.
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private static final class Window {
        final long start;
        final AtomicInteger count = new AtomicInteger(0);

        Window(long start) {
            this.start = start;
        }
    }
}
