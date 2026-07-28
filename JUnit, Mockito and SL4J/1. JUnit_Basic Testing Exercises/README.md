# JUnit — Basic Testing Exercises

Maven project demonstrating JUnit 4 fundamentals (Exercises 1–4).

## Exercise 1: Setting Up JUnit
A Maven project with the JUnit 4.13.2 dependency (`test` scope) declared in
[`pom.xml`](pom.xml), plus the standard Maven layout (`src/main/java`,
`src/test/java`). The `maven-surefire-plugin` is pinned to 3.2.5 so the test run
works cleanly on modern JDKs.

## Exercise 2: Writing Basic JUnit Tests
- Subject: `Calculator` (`add`, `subtract`, `multiply`, `divide`, `isEven`).
- Tests: `CalculatorTest` — one `@Test` per behavior, including an expected
  exception (`@Test(expected = ArithmeticException.class)`).

## Exercise 3: Assertions in JUnit
- `AssertionsTest` demonstrates `assertEquals`, `assertTrue`, `assertFalse`,
  `assertNull`, `assertNotNull`, plus `assertEquals` with a delta,
  `assertArrayEquals`, and `assertSame`/`assertNotSame`.

## Exercise 4: AAA Pattern, Fixtures, Setup & Teardown
- Subject: `BankAccount` (stateful — a good fit for per-test fixtures).
- `BankAccountTest` uses the **Arrange-Act-Assert** structure and the lifecycle
  annotations:
  - `@BeforeClass` / `@AfterClass` — run once per class (static).
  - `@Before` / `@After` — run before/after every test; each test gets a fresh
    `BankAccount` so tests stay independent.

## Project layout
```
pom.xml
src/main/java/com/digitalnurture/Calculator.java
src/main/java/com/digitalnurture/BankAccount.java
src/test/java/com/digitalnurture/CalculatorTest.java
src/test/java/com/digitalnurture/AssertionsTest.java
src/test/java/com/digitalnurture/BankAccountTest.java
```

## Run
```bash
mvn test
```
Runs all tests via Surefire. Reports are written to `target/surefire-reports/`.
