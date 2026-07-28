# Exercise 4 — Resilient Payment Service (Circuit Breaker)

A `payment-service` that calls a slow/unreliable third-party payment API,
protected by a **Resilience4j** circuit breaker with a fallback.

| Component | Role |
|---|---|
| `ThirdPartyPaymentClient` | Simulates the external API (always fails here) |
| `PaymentService` | `@CircuitBreaker(name="paymentService", fallbackMethod="paymentFallback")` around the call |
| `CircuitBreakerEventLogger` | Logs circuit-breaker events (errors, state transitions, blocked calls) |
| `PaymentController` | `POST /payments?orderId=..&amount=..` |

## Requirements covered
- **Circuit Breaker + fallback** — Resilience4j `@CircuitBreaker`; when the call
  fails (or the circuit is open) the `paymentFallback` method returns a graceful
  "queued" response instead of an error.
- **Log & monitor fallback events** — the fallback logs a `WARN`; the
  `CircuitBreakerEventLogger` logs errors/state transitions; and events are
  exposed via actuator: `/actuator/circuitbreakers`,
  `/actuator/circuitbreakerevents`, `/actuator/health`.

> **Dependency note:** the exercise lists `resilience4j-spring-boot2` (Spring
> Boot 2). On Boot 3 the correct dependency is **`resilience4j-spring-boot3`**
> (used here) plus `spring-boot-starter-aop`.

## Run
```bash
cd payment-service && mvn spring-boot:run     # :8087
curl -X POST "localhost:8087/payments?orderId=ORD-1&amount=250"
# -> {"result":"Payment for order ORD-1 is temporarily queued (fallback)."}
curl localhost:8087/actuator/circuitbreakers
```

## Test
```bash
cd payment-service && mvn test    # 2 tests
```
Both assert the fallback response is returned when the third-party call fails;
the fallback `WARN` log is visible in the test output.
