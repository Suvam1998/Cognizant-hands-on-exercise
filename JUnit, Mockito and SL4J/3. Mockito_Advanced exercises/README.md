# Advanced Mockito Hands-On Exercises

Maven project (JUnit 5 + Mockito 5) showing how to mock different kinds of
dependencies — database, REST API, file I/O, network — plus multiple return
values (Exercises 1–5).

## Dependencies
- `org.junit.jupiter:junit-jupiter:5.10.2`
- `org.mockito:mockito-core:5.11.0`
- `org.mockito:mockito-junit-jupiter:5.11.0`
- `maven-surefire-plugin:3.2.5`

## Exercises
| # | Topic | Dependency (mocked) → Service | Test class |
|---|---|---|---|
| 1 | Database / Repository | `Repository` → `Service` | `ServiceTest` |
| 2 | External REST API | `RestClient` → `ApiService` | `ApiServiceTest` |
| 3 | File I/O | `FileReader`, `FileWriter` → `FileService` | `FileServiceTest` |
| 4 | Network interactions | `NetworkClient` → `NetworkService` | `NetworkServiceTest` |
| 5 | Multiple return values | `Repository` → `Service` | `MultiReturnServiceTest` |

Each test follows the same pattern: `mock()` the dependency, stub it with
`when().thenReturn()`, run the service, and `assertEquals` on the result (with a
`verify()` on the interaction).

> Note: `FileReader` / `FileWriter` here are **custom interfaces** in
> `com.digitalnurture`, not `java.io` classes.

## Run
```bash
mvn test
```
