# Edge Services & API Gateway with Spring Boot 3 and Spring Cloud

Three self-contained Spring Cloud Gateway (reactive) projects covering routing &
filtering, load balancing, and resilience patterns.

- Spring Boot **3.4.1**, Spring Cloud **2024.0.0**, Java 17 (verified on JDK 24).
- Each folder is an independent Maven module — `cd` into it and run `mvn test`.

## Projects
| # | Folder | Scenario | Verified by |
|---|---|---|---|
| 1 | `Exercise1_EdgeGateway` | Routing + a `GlobalFilter` that logs requests | route `example_route` registered; `LoggingFilter` bean present |
| 2 | `Exercise2_LoadBalancing` | `lb://` route + custom `RandomLoadBalancer` | route `load_balanced_route` registered; LB bean present |
| 3 | `Exercise3_Resilience` | CircuitBreaker filter + fallback | unreachable backend → fallback (503) via `WebTestClient` |

## Exercise 1 — Edge Service (Routing & Filtering)
- `application.properties` routes `/example/**` → `http://example.org`.
- `LoggingFilter implements GlobalFilter` logs every request (uses SLF4J instead
  of `System.out`).

## Exercise 2 — Load Balancing
- `application.properties` routes `/loadbalanced/**` → `lb://example-service`.
- `LoadBalancerConfiguration` supplies a `RandomLoadBalancer` (replacing the
  default round-robin). A static `simple` discovery instance list is provided so
  `lb://` resolves without a discovery server.
- In production, apply the config per service with
  `@LoadBalancerClient(name = "example-service", configuration = LoadBalancerConfiguration.class)`.

## Exercise 3 — Resilience Patterns
- Route `/cb/**` has a `CircuitBreaker` filter (`fallbackUri=forward:/fallback`);
  `FallbackController` serves the fallback.
- `ResilienceConfiguration` provides the default circuit-breaker / time-limiter
  customizer; `application.yml` sets the `exampleCircuitBreaker` instance.
- **Dependency note:** the exercise lists `resilience4j-spring-boot2`, which is
  for Spring Boot **2**. On Boot 3 the correct dependency is
  `spring-cloud-starter-circuitbreaker-reactor-resilience4j` (used here).

## Note on the sample code
Spring Cloud Gateway is a **reactive (WebFlux)** application — these projects do
not use `spring-boot-starter-web`. The `GlobalFilter`, `ServerWebExchange`, and
`Mono` types in the exercise are the reactive gateway API.

## Run
```bash
cd Exercise1_EdgeGateway   && mvn test
cd Exercise2_LoadBalancing && mvn test
cd Exercise3_Resilience    && mvn test
```
