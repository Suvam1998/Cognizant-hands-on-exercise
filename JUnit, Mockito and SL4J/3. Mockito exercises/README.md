# Mockito Hands-On Exercises

Maven project (JUnit 5 + Mockito 5) demonstrating core Mockito techniques
(Exercises 1–7).

## Dependencies
- `org.junit.jupiter:junit-jupiter:5.10.2`
- `org.mockito:mockito-core:5.11.0`
- `org.mockito:mockito-junit-jupiter:5.11.0` (for `@ExtendWith(MockitoExtension.class)`)
- `maven-surefire-plugin:3.2.5`

## Shared test doubles
- `ExternalApi` — the dependency we **mock** (`getData`, `getDataById`,
  `saveData` (void), `connect`, `disconnect`).
- `MyService` — subject-under-test; takes `ExternalApi` via constructor injection.

## Exercises
| # | Topic | Test class | Key API |
|---|---|---|---|
| 1 | Mocking & Stubbing | `MockingStubbingTest` | `mock()`, `when().thenReturn()` |
| 2 | Verifying Interactions | `VerifyingInteractionsTest` | `verify()`, `times()` |
| 3 | Argument Matching | `ArgumentMatchingTest` | `anyInt()`, `eq()` (+ `@Mock`/extension) |
| 4 | Void Methods | `VoidMethodsTest` | `doNothing().when()`, `verify()` |
| 5 | Multiple Returns | `MultipleReturnsTest` | `thenReturn(a, b, c)` |
| 6 | Interaction Order | `InteractionOrderTest` | `InOrder` |
| 7 | Void Method + Exception | `VoidMethodExceptionTest` | `doThrow().when()`, `assertThrows` |

## Run
```bash
mvn test
```
Reports are written to `target/surefire-reports/`.
