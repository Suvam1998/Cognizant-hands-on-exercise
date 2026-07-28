# Exercise 7: Observer Pattern

**Intent:** Define a one-to-many dependency so that when one object (the subject)
changes state, all its dependents (observers) are notified automatically.

**Participants**
- Subject: `Stock` (register / deregister / notify).
- Concrete subject: `StockMarket` — holds price + observer list; `setPrice()`
  notifies all observers.
- Observer: `Observer` (`update(symbol, price)`).
- Concrete observers: `MobileApp`, `WebApp`.

The test shows both clients updating on price changes, and only the remaining
client updating after one unsubscribes.

**Run**
```bash
javac ObserverPatternExample/*.java
java  ObserverPatternExample.ObserverTest
```
