# Microservices with API Gateway

Hands-on for the bank microservices + Eureka discovery + Spring Cloud API
Gateway exercise. See [THEORY.md](THEORY.md) for the monolith-vs-microservices
background.

## Modules
| Module | Port | Purpose |
|---|---|---|
| `account` | 8080 | `GET /accounts/{number}` → dummy account (Eureka client) |
| `loan` | 8081 | `GET /loans/{number}` → dummy loan (Eureka client) |
| `greet-service` | 8082 | `GET /greet` → "Hello World" (Eureka client) |
| `eureka-discovery-server` | 8761 | Eureka registry (`@EnableEurekaServer`) |
| `api-gateway` | 9090 | Spring Cloud Gateway: discovery-based routing + global `LogFilter` |

Group is `com.cognizant` as specified in the exercise. Stack: Spring Boot 3.4.1,
Spring Cloud 2024.0.0, Java 17 (verified on JDK 24).

## Sample responses
- `GET /accounts/00987987973432` → `{ "number":"00987987973432", "type":"savings", "balance":234343 }`
- `GET /loans/H00987987972342` → `{ "number":"H00987987972342", "type":"car", "loan":400000, "emi":3258, "tenure":18 }`

## Run the full system
```bash
cd eureka-discovery-server && mvn spring-boot:run   # 1) registry :8761
cd account                 && mvn spring-boot:run   # 2) :8080  -> registers
cd loan                    && mvn spring-boot:run   # 3) :8081  -> registers
cd greet-service           && mvn spring-boot:run   # 4) :8082  -> registers
cd api-gateway             && mvn spring-boot:run   # 5) :9090  -> registers
```
- Eureka dashboard (see registered services): http://localhost:8761
- Through the gateway (discovery locator, lower-case id):
  `http://localhost:9090/greet-service/greet` → "Hello World"
- The gateway console logs each request via the global `LogFilter`:
  `Incoming request: GET http://localhost:9090/greet-service/greet`

## Tests
```bash
cd account                 && mvn test   # account endpoint
cd loan                    && mvn test   # loan endpoint
cd greet-service           && mvn test   # greet endpoint
cd eureka-discovery-server && mvn test   # registry context loads
cd api-gateway             && mvn test   # LogFilter registered + delegates to chain
```
Service tests run standalone — `src/test/resources/application.properties`
disables the Eureka client (and the gateway's discovery locator) so no registry
needs to be running.
