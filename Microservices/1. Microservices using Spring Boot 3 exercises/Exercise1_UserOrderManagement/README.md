# Exercise 1 — User & Order Management System

Two Spring Boot 3 microservices communicating over REST.

| Module | Port | Responsibility |
|---|---|---|
| `user-service` | 8081 | CRUD for users (JPA) |
| `order-service` | 8082 | CRUD for orders; validates the user via the User Service before creating an order |

## Requirements covered
- **REST APIs** — `/users` and `/orders`.
- **Inter-service communication** — `order-service` calls `user-service` using
  **WebClient** (`UserClient`) to verify the user exists before saving an order.
- **Database** — JPA persistence. Runs on **H2** out of the box; switch to
  MySQL/PostgreSQL by adding the driver and editing `application.yml` (a MySQL
  example is shown in comments there).

## Run locally
```bash
cd user-service  && mvn spring-boot:run     # starts on :8081
cd order-service && mvn spring-boot:run     # starts on :8082
```
Then:
```bash
curl -X POST localhost:8081/users  -H "Content-Type: application/json" -d '{"name":"Alice","email":"alice@example.com"}'
curl -X POST localhost:8082/orders -H "Content-Type: application/json" -d '{"userId":1,"product":"Book","quantity":2}'
```
The order call makes `order-service` reach `user-service` to confirm user 1 exists.

## Tests
```bash
cd user-service  && mvn test    # 2 tests
cd order-service && mvn test    # 2 tests (UserClient mocked with @MockitoBean)
```
`order-service` tests mock `UserClient`, so they run without a live `user-service`.
