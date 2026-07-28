# Employee Management System — Spring Data JPA & Hibernate

A single Spring Boot app covering all 10 exercises: entities & relationships,
repositories, CRUD REST, custom/named queries, pagination & sorting, auditing,
projections, data-source config, and Hibernate-specific features.

## Stack
Spring Boot 3.4.1, Spring Web, Spring Data JPA, H2, Lombok, Java 17.

## Exercise → implementation
| # | Topic | Where |
|---|---|---|
| 1 | Project + H2 config | `pom.xml`, `application.properties` |
| 2 | Entities + relationship | `Employee`, `Department` (`@OneToMany`/`@ManyToOne`), Lombok |
| 3 | Repositories | `EmployeeRepository`, `DepartmentRepository` (`JpaRepository`) |
| 4 | CRUD REST | `EmployeeController`, `DepartmentController` |
| 5 | Query methods | derived (`findByName`, `findByEmailContaining`, `findByDepartmentName`), `@Query` (`searchByDepartment`), `@NamedQuery` (`findByEmailDomain`) |
| 6 | Pagination & sorting | `GET /employees/page?page&size&sortBy&dir` → `Page`/`Pageable` |
| 7 | Auditing | `Auditable` base + `@CreatedDate/@CreatedBy/@LastModifiedDate/@LastModifiedBy`, `JpaAuditingConfig` (`AuditorAware`) |
| 8 | Projections | `EmployeeView` (interface + `@Value`), `EmployeeDto` (constructor expression) |
| 9 | Data-source config | Spring Boot auto-config from `spring.datasource.*`; multi-DS notes in `application.properties` |
| 10 | Hibernate features | batch props (`hibernate.jdbc.batch_size`, `order_inserts/updates`), dialect config, `saveAll` batch |

## Run
```bash
mvn spring-boot:run          # http://localhost:8080
```
H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:testdb`).

### Sample requests
```bash
curl -X POST localhost:8080/departments -H "Content-Type: application/json" -d '{"name":"Engineering"}'
curl -X POST localhost:8080/employees   -H "Content-Type: application/json" \
     -d '{"name":"Dave","email":"dave@corp.com","department":{"id":1}}'
curl "localhost:8080/employees/page?page=0&size=5&sortBy=name&dir=asc"
```

## Tests
```bash
mvn test
```
`EmployeeRepositoryTest` (8) + `EmployeeControllerTest` (1) = **9 tests**, all
passing — covering derived/custom/named queries, pagination+sorting, both
projection styles, auditing timestamps, batch save, and the CRUD/pagination REST
flow.

## Notes
- **Lombok** is pinned to **1.18.42** (Boot 3.4.1's managed 1.18.36 crashes on
  JDK 24's javac). Entities use `@Getter/@Setter/@NoArgsConstructor`.
- `Department` is annotated `@JsonIgnoreProperties({"hibernateLazyInitializer",
  "handler"})` so a lazy Hibernate proxy serializes cleanly over REST.
- **Exercise 9 (multiple data sources)** is documented in
  `application.properties` (primary auto-configured; secondary DS pattern shown)
  rather than fully wired, to keep the app single-datasource and runnable.
