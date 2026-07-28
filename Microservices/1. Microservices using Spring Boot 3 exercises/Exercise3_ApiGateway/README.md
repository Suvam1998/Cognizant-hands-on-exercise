# Exercise 3 — API Gateway

Three modules: an API Gateway routing to two backend services.

| Module | Port | Role |
|---|---|---|
| `api-gateway` | 8080 | Spring Cloud Gateway — routing, path rewriting, caching, rate limiting |
| `customer-service` | 8085 | `/customers`, `/customers/{id}` |
| `billing-service` | 8086 | `/billing/{customerId}` |

## Requirements covered
- **Spring Cloud Gateway** — two routes (`customer_route`, `billing_route`).
- **Path rewriting** — `RewritePath` maps the public path to the backend path:
  - `/api/customers/**` → customer-service `/customers/**`
  - `/api/billing/**` → billing-service `/billing/**`
- **Caching** — `LocalResponseCache` caches successful GET responses in-process
  (`spring.cloud.gateway.filter.local-response-cache`), no Redis required.
- **Rate limiting** — a custom in-memory `RateLimitingFilter` (fixed window per
  client IP) returns **429** past the limit.

> **Production note:** for distributed rate limiting across multiple gateway
> instances, use the built-in `RequestRateLimiter` filter backed by Redis
> (`spring-boot-starter-data-redis-reactive` + a `KeyResolver`). The in-memory
> filter here keeps the sample self-contained and testable.

## Run
```bash
cd customer-service && mvn spring-boot:run    # :8085
cd billing-service  && mvn spring-boot:run    # :8086
cd api-gateway      && mvn spring-boot:run    # :8080
```
Then through the gateway:
```bash
curl localhost:8080/api/customers/1   # -> customer-service /customers/1
curl localhost:8080/api/billing/1     # -> billing-service  /billing/1
# 6th rapid request from the same client within 10s -> HTTP 429
```

## Tests
```bash
cd customer-service && mvn test   # endpoint returns customers
cd billing-service  && mvn test   # endpoint returns an invoice
cd api-gateway      && mvn test   # routes registered + rate limiter returns 429
```
