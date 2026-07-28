# Exercise 1: Singleton Pattern

**Intent:** Ensure a class has only one instance and provide a global point of
access to it — here, a shared `Logger`.

**How it's enforced**
- `private` constructor — nothing outside the class can `new` a `Logger`.
- `private static` instance held by the class.
- `public static getInstance()` returns that same instance every time.
- Uses the thread-safe **initialization-on-demand holder** idiom (lazy + safe
  without synchronization overhead).

**Files**
- `Logger.java` — the singleton.
- `SingletonTest.java` — proves two `getInstance()` calls return the same object
  (`logger1 == logger2` is `true`).

**Run**
```bash
javac SingletonPatternExample/*.java
java  SingletonPatternExample.SingletonTest
```
