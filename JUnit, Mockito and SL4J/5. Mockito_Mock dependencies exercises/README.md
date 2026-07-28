# Mocking Dependencies in Spring Tests (with Mockito)

Spring Boot 3.4 project showing three ways to replace a dependency with a
Mockito mock in Spring tests (Exercises 1–3).

## Stack
- Spring Boot 3.4.1 (`web`, `data-jpa`), H2 (in-memory), `spring-boot-starter-test`.

## Application (`src/main/java/com/digitalnurture`)
- `User` — JPA entity (mapped to table **`users`**; `USER` is SQL-reserved).
- `UserRepository extends JpaRepository<User, Long>`.
- `UserService.getUserById(id)` — depends on the repository.
- `UserController` — `GET /users/{id}` delegating to the service.

## Exercises → tests
| # | Task | Test | Mock technique |
|---|---|---|---|
| 1 | Mock a **service** in a **controller** test | `UserControllerTest` | `@WebMvcTest` + `@MockitoBean UserService` + MockMvc |
| 2 | Mock a **repository** in a **service** test | `UserServiceTest` | `@Mock` + `@InjectMocks` (MockitoExtension, no Spring) |
| 3 | Mock a **service** in an **integration** test | `UserIntegrationTest` | `@SpringBootTest` + `@AutoConfigureMockMvc` + `@MockitoBean` |

- **Ex1** loads only the web slice and stubs the service, so the controller is
  tested in isolation.
- **Ex2** is a pure unit test — the MockitoExtension injects the mocked
  repository into the real service.
- **Ex3** boots the whole context but overrides the service bean with a mock, so
  the request goes through the real web stack while the service is stubbed.

> Uses `@MockitoBean` (Spring Framework 6.2) — the current replacement for the
> deprecated `@MockBean`.

## Run
```bash
mvn test
```
