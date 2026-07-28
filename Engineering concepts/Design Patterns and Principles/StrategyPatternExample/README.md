# Exercise 8: Strategy Pattern

**Intent:** Define a family of algorithms, encapsulate each one, and make them
interchangeable at runtime. The algorithm varies independently of clients.

**Participants**
- Strategy: `PaymentStrategy` (`pay(amount)`).
- Concrete strategies: `CreditCardPayment`, `PayPalPayment`.
- Context: `PaymentContext` — holds a strategy and delegates `checkout()` to it;
  the strategy can be swapped via `setPaymentStrategy()`.

**Run**
```bash
javac StrategyPatternExample/*.java
java  StrategyPatternExample.StrategyTest
```
