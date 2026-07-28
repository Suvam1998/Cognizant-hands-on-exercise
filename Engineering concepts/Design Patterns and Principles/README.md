# Design Patterns & Principles — Hands-On Exercises

Eleven Java exercises covering the Gang-of-Four design patterns plus MVC and
Dependency Injection. Each exercise is a self-contained Java package (matching
the "create a new Java project named X" instruction) with the pattern classes,
a runnable test/main class, and its own `README.md`.

| # | Pattern | Category | Project folder | Main class |
|---|---|---|---|---|
| 1 | Singleton | Creational | `SingletonPatternExample` | `SingletonTest` |
| 2 | Factory Method | Creational | `FactoryMethodPatternExample` | `FactoryMethodTest` |
| 3 | Builder | Creational | `BuilderPatternExample` | `BuilderTest` |
| 4 | Adapter | Structural | `AdapterPatternExample` | `AdapterTest` |
| 5 | Decorator | Structural | `DecoratorPatternExample` | `DecoratorTest` |
| 6 | Proxy | Structural | `ProxyPatternExample` | `ProxyTest` |
| 7 | Observer | Behavioral | `ObserverPatternExample` | `ObserverTest` |
| 8 | Strategy | Behavioral | `StrategyPatternExample` | `StrategyTest` |
| 9 | Command | Behavioral | `CommandPatternExample` | `CommandTest` |
| 10 | MVC | Architectural | `MVCPatternExample` | `MVCTest` |
| 11 | Dependency Injection | Principle | `DependencyInjectionExample` | `DITest` |

## Requirements
- JDK 8 or newer (developed/verified on JDK 24).

## Build & run
Each folder is its own package. From **this** directory:

```bash
# Example — Singleton
javac SingletonPatternExample/*.java
java  SingletonPatternExample.SingletonTest
```

Substitute the folder and main class from the table for any other exercise.
Each exercise's own `README.md` explains the pattern's intent and participants.
