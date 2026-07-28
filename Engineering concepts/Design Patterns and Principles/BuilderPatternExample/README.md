# Exercise 3: Builder Pattern

**Intent:** Construct a complex object step by step, separating construction from
representation. Ideal when an object has many optional parts.

**Design**
- `Computer` is immutable with a `private` constructor taking a `Builder`.
- The static nested `Computer.Builder` takes required fields (`cpu`, `ram`) in
  its constructor and exposes fluent `with`-style methods for optional fields
  (`storage`, `gpu`, `wifi`, `bluetooth`), ending in `build()`.

**Run**
```bash
javac BuilderPatternExample/*.java
java  BuilderPatternExample.BuilderTest
```
