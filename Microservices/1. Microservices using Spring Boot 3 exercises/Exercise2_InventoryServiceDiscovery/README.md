# Exercise 2 — Inventory Management with Service Discovery & Central Config

Four Spring Boot 3 / Spring Cloud modules.

| Module | Port | Role |
|---|---|---|
| `eureka-server` | 8761 | Netflix Eureka service registry (`@EnableEurekaServer`) |
| `config-server` | 8888 | Spring Cloud Config Server (`@EnableConfigServer`, native profile) |
| `product-service` | 8083 | Manage products & stock (JPA); Eureka + Config client |
| `inventory-service` | 8084 | Track stock levels per product (JPA); Eureka + Config client |

## Requirements covered
- **Service discovery** — `product-service` and `inventory-service` register with
  **Eureka** (`eureka.client.service-url.defaultZone`).
- **Centralized configuration** — the **Config Server** serves per-service config
  from `config-server/src/main/resources/config/{service}.yml`; each service
  imports it via `spring.config.import=optional:configserver:...`.
- **Database** — JPA on H2 (swap for MySQL/PostgreSQL as in Exercise 1).

## Start order (for a real run)
```bash
cd eureka-server    && mvn spring-boot:run     # 1) registry   :8761
cd config-server    && mvn spring-boot:run     # 2) config     :8888
cd product-service  && mvn spring-boot:run     # 3) registers + pulls config
cd inventory-service&& mvn spring-boot:run     # 4) registers + pulls config
```
- Eureka dashboard: http://localhost:8761
- Config check: http://localhost:8888/product-service/default

## Endpoints
- Product: `GET/POST /products`, `GET /products/{id}`
- Inventory: `GET/POST /inventory`, `GET /inventory/{productId}`,
  `GET /inventory/{productId}/available?quantity=N`

## Tests
```bash
cd eureka-server     && mvn test    # context loads (registry starts)
cd config-server     && mvn test    # context loads (config server starts)
cd product-service   && mvn test    # product CRUD
cd inventory-service && mvn test    # stock upsert + availability check
```
Service tests run standalone — `src/test/resources/application.yml` disables the
Eureka client and Config import so they don't need the servers running.
