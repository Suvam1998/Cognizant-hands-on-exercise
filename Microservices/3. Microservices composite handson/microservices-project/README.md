# Microservices Composite Hands-on

A complete Spring Boot 3 / Spring Cloud microservices system: two business
services, a greet service, a Eureka discovery server, and a Spring Cloud API
Gateway with a global logging filter.

## Project overview
Independent services register with **Eureka**; the **API Gateway** discovers them
and routes requests by service id, logging every incoming request via a global
`LogFilter`.

## Architecture (ASCII)
```
                         ┌─────────────────────────┐
                         │   Eureka Server (8761)   │
                         │  service registry        │
                         └───────────▲─────────────┘
              register / discover     │
        ┌───────────────┬─────────────┼───────────────┐
        │               │             │               │
 ┌──────┴──────┐ ┌──────┴──────┐ ┌────┴────────┐      │
 │  account    │ │   loan      │ │  greet      │      │ register
 │  :8080      │ │   :8081     │ │  :8082      │      │
 └─────────────┘ └─────────────┘ └─────────────┘      │
        ▲                                        ┌─────┴───────┐
        │                                        │ API Gateway │
   client ── HTTP ──────────────────────────────►   :9090     │
        (via gateway: /greet-service/greet)      │  LogFilter  │
                                                 └─────────────┘
```

## Technologies used
- Java 17, Spring Boot 3.4.1, Spring Cloud 2024.0.0, Maven
- Spring Web, Netflix Eureka Server, Eureka Discovery Client
- Spring Cloud Gateway, Spring Boot Actuator

## Modules & ports
| Service | Port | Application name | Key endpoint |
|---|---|---|---|
| `eureka-server` | 8761 | eureka-server | Dashboard at `/` |
| `account-service` | 8080 | account-service | `GET /accounts/{number}` |
| `loan-service` | 8081 | loan-service | `GET /loans/{number}` |
| `greet-service` | 8082 | greet-service | `GET /greet` |
| `api-gateway` | 9090 | api-gateway | routes `/{service-id}/**` |

## How to run (in order)
Build everything first:
```bash
# from each module folder
mvn clean package
```
Then launch in this order (each in its own terminal):
```bash
cd eureka-server   && mvn spring-boot:run     # 1
cd account-service && mvn spring-boot:run     # 2
cd loan-service    && mvn spring-boot:run     # 3
cd greet-service   && mvn spring-boot:run     # 4
cd api-gateway     && mvn spring-boot:run     # 5
```
(You can also run the packaged jar: `java -jar target/<service>-0.0.1-SNAPSHOT.jar`.)

Open the Eureka dashboard at http://localhost:8761 — all four clients
(account, loan, greet, api-gateway) should appear under *Instances currently
registered with Eureka*.

## API testing (browser / Postman)
| Test | URL |
|---|---|
| Account (direct) | `GET http://localhost:8080/accounts/00987987973432` |
| Loan (direct) | `GET http://localhost:8081/loans/H00987987972342` |
| Greet (direct) | `GET http://localhost:8082/greet` |
| Greet (via gateway) | `GET http://localhost:9090/greet-service/greet` |

Sample responses:
```json
// GET /accounts/00987987973432
{ "number": "00987987973432", "type": "savings", "balance": 234343 }

// GET /loans/H00987987972342
{ "number": "H00987987972342", "type": "car", "loan": 400000, "emi": 3258, "tenure": 18 }

// GET /greet
Hello World!!
```
When you call the gateway URL, the `api-gateway` console prints:
```
Incoming Request URL: /greet-service/greet
```

## Testing
Each module has unit/slice tests that run standalone (Eureka disabled in
`src/test/resources`):
```bash
cd <module> && mvn test
```

## Troubleshooting guide
| Symptom | Cause / Fix |
|---|---|
| `Port 8080/8081/... already in use` | Another process holds the port. Stop it, or change `server.port`. Each service must use its own port. |
| Service not shown in Eureka dashboard | Start `eureka-server` **first** and wait until it's up; check `eureka.client.service-url.defaultZone=http://localhost:8761/eureka`. Registration can take ~30s. |
| `404` at `http://localhost:9090/greet-service/greet` | greet-service not registered yet, or discovery locator disabled. Confirm greet-service appears in Eureka, and that `spring.cloud.gateway.discovery.locator.enabled=true`. |
| `503 Service Unavailable` from the gateway | The target instance is down or still registering; wait and retry. |
| Gateway won't start: "spring.main.web-application-type" / servlet vs reactive | Don't add `spring-boot-starter-web` to the gateway — Spring Cloud Gateway is reactive (Netty). |
| `Connection refused: localhost:8761` in a client log | Eureka server isn't running yet. Start it first; clients retry automatically. |
| Tests try to reach Eureka | The provided `src/test/resources/application.properties` sets `eureka.client.enabled=false`; keep it. |
```
