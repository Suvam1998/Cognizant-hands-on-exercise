# Microservices with Spring Boot 3.0 — Exercises

Four exercises, each a folder of independent, runnable Spring Boot 3 / Spring
Cloud modules. Every module has passing tests; `cd` into any module and run
`mvn test`.

| # | Folder | What it demonstrates | Modules |
|---|---|---|---|
| 1 | `Exercise1_UserOrderManagement` | REST + inter-service calls (WebClient) + JPA | user-service, order-service |
| 2 | `Exercise2_InventoryServiceDiscovery` | Eureka service discovery + Spring Cloud Config | eureka-server, config-server, product-service, inventory-service |
| 3 | `Exercise3_ApiGateway` | Spring Cloud Gateway: routing, path rewriting, caching, rate limiting | api-gateway, customer-service, billing-service |
| 4 | `Exercise4_ResilientPayment` | Resilience4j circuit breaker + fallback + event logging | payment-service |

Common stack: **Spring Boot 3.4.1**, **Spring Cloud 2024.0.0**, Java 17
(verified on JDK 24). See each exercise's own `README.md` for run/test details.

## Notes / substitutions (all documented per exercise)
- **Database**: exercises say MySQL/PostgreSQL; these use in-memory **H2** so they
  run with no external DB. Each `application.yml` shows how to switch drivers.
- **Rate limiting** (Ex3): a self-contained in-memory filter is used instead of
  the Redis-backed `RequestRateLimiter` (noted for production).
- **Resilience4j** (Ex4): uses `resilience4j-spring-boot3` — the exercise's
  `-spring-boot2` artifact is for Spring Boot 2 and won't work on Boot 3.
- Infra servers (Eureka, Config) are verified with context-load tests; the
  business services' tests run standalone (discovery/config disabled in
  `src/test/resources`).
