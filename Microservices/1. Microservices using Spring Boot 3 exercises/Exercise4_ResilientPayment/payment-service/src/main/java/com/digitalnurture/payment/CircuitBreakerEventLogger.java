package com.digitalnurture.payment;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

/**
 * Monitors circuit-breaker events (errors, state transitions, calls-not-permitted)
 * and logs them — satisfying the "log and monitor fallback events" requirement.
 * These events are also exposed via the actuator endpoints
 * /actuator/circuitbreakers and /actuator/circuitbreakerevents.
 */
@Component
public class CircuitBreakerEventLogger {

    private static final Logger log = LoggerFactory.getLogger(CircuitBreakerEventLogger.class);

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerEventLogger(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void registerListeners() {
        registry.getAllCircuitBreakers().forEach(this::attach);
        // Attach to any circuit breaker created later (they are created lazily).
        registry.getEventPublisher()
                .onEntryAdded(event -> attach(event.getAddedEntry()));
    }

    private void attach(io.github.resilience4j.circuitbreaker.CircuitBreaker cb) {
        cb.getEventPublisher()
                .onError(e -> log.warn("[CB:{}] call failed: {}",
                        cb.getName(), e.getThrowable().toString()))
                .onStateTransition(e -> log.info("[CB:{}] state {} -> {}",
                        cb.getName(),
                        e.getStateTransition().getFromState(),
                        e.getStateTransition().getToState()))
                .onCallNotPermitted(e -> log.warn("[CB:{}] call blocked (circuit OPEN)",
                        cb.getName()));
    }
}
