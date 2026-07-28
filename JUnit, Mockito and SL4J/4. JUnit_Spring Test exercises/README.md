# Spring Testing Exercises

Spring Boot 3.4 application demonstrating the full range of Spring test slices
(Exercises 1–9). Uses JUnit 5, Mockito, MockMvc, and an in-memory H2 database.

## Stack
- Spring Boot 3.4.1 (`spring-boot-starter-web`, `spring-boot-starter-data-jpa`).
- H2 (in-memory) for JPA and integration tests.
- `spring-boot-starter-test` (JUnit 5 + Mockito + MockMvc + AssertJ + JSONPath).

## Application under test (`src/main/java/com/digitalnurture`)
- `CalculatorService` — `add(a, b)`.
- `User` — JPA entity (mapped to table **`users`** since `USER` is a SQL
  reserved word; `@GeneratedValue` so `save()` assigns the id).
- `UserRepository` — `JpaRepository` + custom `findByName`.
- `UserService` — `getUserById`, `getUserByIdOrThrow`, `saveUser`, `getUsersByName`.
- `UserController` — `GET /users/{id}`, `POST /users` (throws
  `NoSuchElementException` when a user is missing).
- `GlobalExceptionHandler` — `@ControllerAdvice` mapping that exception to 404.

## Exercises → tests
| # | Task | Test | Technique |
|---|---|---|---|
| 1 | Unit test a service method | `CalculatorServiceTest.testAdd` | plain JUnit |
| 2 | Mock a repository in a service test | `UserServiceTest.testGetUserById` | `@Mock` + `@InjectMocks` |
| 3 | Test a REST controller | `UserControllerTest.testGetUser` | `@WebMvcTest` + MockMvc |
| 4 | Integration test (controller → DB) | `UserIntegrationTest` | `@SpringBootTest` + `@AutoConfigureMockMvc` + H2 |
| 5 | Test POST endpoint | `UserControllerTest.testCreateUser` | MockMvc POST + JSON |
| 6 | Service exception handling | `UserServiceTest.testGetUserByIdOrThrow…` | `assertThrows` |
| 7 | Custom repository query | `UserRepositoryTest.testFindByName` | `@DataJpaTest` |
| 8 | Controller exception handling | `UserControllerTest.testGetUserNotFound` | `@ControllerAdvice` → 404 |
| 9 | Parameterized test | `CalculatorServiceTest.testAddParameterized` | `@ParameterizedTest` + `@CsvSource` |

> `@MockitoBean` (Spring Framework 6.2) is used instead of the deprecated
> `@MockBean` to inject the mocked service into the web-layer test.

## Run
```bash
mvn test
```
Runs all 14 tests. Reports land in `target/surefire-reports/`.
