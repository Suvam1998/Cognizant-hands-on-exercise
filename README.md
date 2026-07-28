# Cognizant Digital Nurture — Hands-On Exercises

A consolidated collection of hands-on exercises from the Cognizant Digital
Nurture (.NET / Java Full Stack Engineering) program, covering core Java, data
structures & algorithms, design patterns, testing, logging, Spring (Core, REST,
Data JPA, Security), microservices with Spring Cloud, Angular, and PL/SQL.

Every Java / Spring / Angular project in this repository has been **built and
test-verified**, and each worked folder includes an `output-screenshot.png`
rendered from the real program / test output.

## Tech stack
| Area | Technologies |
|---|---|
| Language / build | Java 17 (built on JDK 24), Maven 3.9 |
| Core / testing | JUnit 5, Mockito 5, SLF4J + Logback, AssertJ |
| Spring | Spring Framework 6.2, Spring Boot 3.4, Spring Data JPA (Hibernate 6), Spring Security 6, Spring Cloud 2024.0 |
| Datastores | H2 (in-memory), MySQL config, Oracle PL/SQL |
| Frontend | Angular 20 (standalone components) |
| Auth | HTTP Basic, JWT (jjwt 0.12), OAuth2 / OIDC |

## Repository structure
```
Cognizant-hands-on-exercise/
├── Angular/                          # Angular Student Course Portal (Hands-On 1)
├── Engineering concepts/
│   ├── Algorithms_Data Structures/   # 7 DSA exercises (Java)
│   └── Design Patterns and Principles/ # 11 GoF + MVC + DI patterns
├── JUnit, Mockito and SL4J/          # JUnit (basic/advanced), Mockito, Spring Test, SLF4J
├── Microservices/                    # Spring Cloud: Eureka, Gateway, Config, Resilience4j, JWT
├── PL SQL programming/               # Oracle PL/SQL: procedures, functions, triggers, cursors, packages
├── Spring Core and Maven/            # Library Management (XML/annotation IoC, AOP) + Spring Boot
├── Spring Data JPA with Hibernate/   # orm-learn (query methods, HQL, criteria) + Employee Mgmt System
└── Spring REST using Spring Boot/    # spring-learn: REST, validation, exception handling, JWT security
```

## What each track covers

### Engineering concepts
- **Algorithms & Data Structures** — inventory (HashMap), search (linear/binary),
  sorting (bubble/quick), arrays, linked lists, recursion, with Big-O analysis.
- **Design Patterns** — Singleton, Factory Method, Builder, Adapter, Decorator,
  Proxy, Observer, Strategy, Command, MVC, and Dependency Injection.

### JUnit, Mockito and SLF4J
- JUnit 5 basics + advanced (parameterized, suites, ordering, timeouts).
- Mockito (stubbing, verification, argument matchers, spies) and mocking Spring
  dependencies (`@WebMvcTest`, `@MockitoBean`, `@DataJpaTest`, integration tests).
- SLF4J + Logback logging (levels, parameterized logging, console + file appenders).

### Spring Core and Maven
- **LibraryManagement** — classic Spring: XML & annotation IoC/DI (constructor +
  setter), and AspectJ AOP (execution-time logging).
- **LibraryManagement (Spring Boot)** — REST CRUD over `Book` with JPA + H2.

### Spring Data JPA with Hibernate
- **orm-learn** — Country/Stock/Employee models: derived query methods, `@Query`,
  named queries, pagination & sorting, HQL `join fetch`, aggregates, native
  queries, and the Criteria API; O/R mapping (`@ManyToOne`, `@OneToMany`, `@ManyToMany`).
- **EmployeeManagementSystem** — entities & relationships, repositories, CRUD REST,
  custom/named queries, pagination, JPA auditing, projections, and Hibernate batch.

### Spring REST using Spring Boot
- **spring-learn** — progressive REST hands-on: Spring Core XML beans → GET
  services → Controller/Service/Dao → POST/PUT/DELETE with Bean Validation and a
  global exception handler → **JWT authentication** with Spring Security.

### Microservices (Spring Cloud)
- Edge services & API Gateway (routing, filters, load balancing, resilience).
- Service discovery (**Eureka**), centralized config (**Config Server**).
- Circuit breaker & fallback (**Resilience4j**).
- Composite system: Eureka + Account/Loan/Greet services + API Gateway with a
  global logging filter.
- Centralized auth & SSO samples: OAuth2/OIDC login, JWT resource server, custom
  JWT filter.

### PL/SQL
- Oracle PL/SQL exercises: control structures, error handling, stored procedures,
  functions, triggers, cursors, and packages (bank domain).

## How to run

**Java exercises** (Algorithms / Design Patterns):
```bash
cd "Engineering concepts/Algorithms_Data Structures/Exercise1_InventoryManagement"
javac *.java && java Exercise1_InventoryManagement.InventoryManagement
```

**Maven projects** (JUnit / Mockito / Spring / Microservices):
```bash
cd <project-with-pom.xml>
mvn test           # run the tests
mvn spring-boot:run   # run a Spring Boot service
```

**Angular** (Student Course Portal):
```bash
cd "Angular/Angular_HandsOn/Suvam/student-course-portal"
npm install && ng serve      # http://localhost:4200
```

**PL/SQL** (Oracle — run in SQL Developer / SQL*Plus):
```sql
@00_schema.sql
@Exercise1_ControlStructures.sql   -- ... through Exercise7
```

## Notes
- Projects target the current stack (Spring Boot 3 / Java 17 / jakarta); where an
  exercise referenced an older API (e.g. `WebSecurityConfigurerAdapter`, jjwt
  0.9, javax), it was modernized to the equivalent that compiles and runs — each
  such change is documented in that project's own `README.md`.
- Databases default to in-memory **H2** so projects run without external servers;
  MySQL/Oracle configuration is provided where the exercise specifies it.
- The PL/SQL scripts were not executed here (no Oracle instance available); all
  other deliverables were compiled and passed their tests.

## Author
**Suvam1998** · 25167038@kiit.ac.in
