# Exercise 4: Adapter Pattern

**Intent:** Convert the interface of a class into another interface clients
expect. Lets classes with incompatible interfaces work together.

**Participants**
- Target: `PaymentProcessor` (what the app expects — `processPayment(amount)`).
- Adaptees: `PayPalGateway.sendPayment(dollars)`,
  `StripeGateway.makeTransaction(cents, currency)` — incompatible, unchangeable.
- Adapters: `PayPalAdapter`, `StripeAdapter` — implement `PaymentProcessor` and
  translate the call (e.g. dollars → cents) to the gateway's own method.

**Run**
```bash
javac AdapterPatternExample/*.java
java  AdapterPatternExample.AdapterTest
```
