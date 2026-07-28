package com.digitalnurture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Exercise 1 — a GlobalFilter that logs every request passing through the
 * gateway. GlobalFilters apply to all routes.
 */
@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Request: {} {}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI());
        return chain.filter(exchange);
    }
}
