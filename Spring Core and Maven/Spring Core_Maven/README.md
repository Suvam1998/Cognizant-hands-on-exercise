# Spring Core & Maven — Library Management Exercises

Two projects covering Exercises 1–9.

| Project | Exercises | Description |
|---|---|---|
| [`LibraryManagement`](LibraryManagement) | 1–8 | Classic Spring (no Boot): XML config, IoC/DI (constructor + setter), annotation config, and AOP logging |
| [`LibraryManagement-SpringBoot`](LibraryManagement-SpringBoot) | 9 | Spring Boot REST app: Web + Data JPA + H2, `Book` CRUD |

Each project has its own README with run/test instructions and its tests pass
via `mvn test`.

## Verification summary
- `LibraryManagement` — `mvn test` green (XML + annotation contexts load, DI
  wired, AOP proxy applied); `mvn exec:java` prints AOP timing + injected result.
- `LibraryManagement-SpringBoot` — `mvn test` green (CRUD create+fetch, 404).

## Version note
The exercises name Java 1.8 / Spring Core (Spring 5). Spring 6 requires Java 17+
and the environment JDK is 24, so:
- `LibraryManagement` uses **Spring 6.2.1 + Java 17**,
- `LibraryManagement-SpringBoot` uses **Spring Boot 3.4.1 + Java 17**.

The concepts demonstrated (IoC/DI, XML & annotation config, AspectJ AOP,
Boot + JPA REST) are exactly those in the exercises.
