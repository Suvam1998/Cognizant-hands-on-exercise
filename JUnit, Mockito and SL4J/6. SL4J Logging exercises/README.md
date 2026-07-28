# Logging using SLF4J

Plain-Java Maven project demonstrating SLF4J with a Logback backend
(Exercises 1–3).

## Dependencies
- `org.slf4j:slf4j-api:2.0.13`
- `ch.qos.logback:logback-classic:1.5.6` (the SLF4J binding)

> The exercise text lists SLF4J 1.7.30 / Logback 1.2.3. Those are several years
> old and can misbehave on recent JDKs, so this project uses the current 2.0.x /
> 1.5.x line — the SLF4J API used in the code is identical.

## Exercises
| # | Topic | Class |
|---|---|---|
| 1 | Error & warning levels | `LoggingExample` |
| 2 | Parameterized logging (`{}` placeholders, exception logging) | `ParameterizedLoggingExample` |
| 3 | Multiple appenders (console + file) via `logback.xml` | `AppenderLoggingExample` |

`src/main/resources/logback.xml` defines a **console** appender and a **file**
appender (`app.log`); the root logger (level `debug`) sends events to both.

## Run
```bash
mvn -q compile

# Exercise 1
mvn -q exec:java -Dexec.mainClass=com.digitalnurture.LoggingExample

# Exercise 2
mvn -q exec:java -Dexec.mainClass=com.digitalnurture.ParameterizedLoggingExample

# Exercise 3 (also writes app.log)
mvn -q exec:java -Dexec.mainClass=com.digitalnurture.AppenderLoggingExample
```

After running Exercise 3, the same lines appear in the console and in `app.log`.
