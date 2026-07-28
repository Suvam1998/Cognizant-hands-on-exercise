# JUnit — Advanced Testing Exercises

Maven project demonstrating advanced JUnit 5 (Jupiter) features (Exercises 1–5).

## Dependencies
- `org.junit.jupiter:junit-jupiter:5.10.2` — API, parameterized tests, engine.
- `org.junit.platform:junit-platform-suite:1.10.2` — `@Suite` support.
- `maven-surefire-plugin:3.2.5` — runs JUnit 5 natively (JDK 24 compatible).

## Exercise 1: Parameterized Tests
- Subject: `EvenChecker.isEven(int)`.
- `EvenCheckerTest` uses `@ParameterizedTest` with `@ValueSource` (even and odd
  inputs) and `@CsvSource` (input → expected pairs) — one method, many cases.

## Exercise 2: Test Suites
- `AllTests` groups the test classes with `@Suite` + `@SelectClasses` (and
  `@SuiteDisplayName`), running them as one suite.

## Exercise 3: Test Execution Order
- `OrderedTests` uses `@TestMethodOrder(OrderAnnotation.class)` with `@Order(1..3)`
  so the methods run in a deterministic sequence (verified by a shared counter).

## Exercise 4: Exception Testing
- Subject: `ExceptionThrower.throwException(int)` (throws on negative input).
- `ExceptionThrowerTest` uses `assertThrows` (and checks the message) plus
  `assertDoesNotThrow` for the happy path.

## Exercise 5: Timeout / Performance Testing
- Subject: `PerformanceTester` (`performTask`, `sumTo`).
- `PerformanceTesterTest` uses the `@Timeout` annotation and the `assertTimeout`
  assertion to fail if the work overruns its time budget.

## Project layout
```
pom.xml
src/main/java/com/digitalnurture/{EvenChecker,ExceptionThrower,PerformanceTester}.java
src/test/java/com/digitalnurture/{EvenCheckerTest,ExceptionThrowerTest,
                                  OrderedTests,PerformanceTesterTest,AllTests}.java
```

## Run
```bash
mvn test
```
> Note: because `AllTests` selects the same classes that Surefire also runs
> directly, those classes execute both standalone and inside the suite — this is
> expected. `OrderedTests` resets its counter in `@BeforeAll` so it passes in
> both runs.
