# Exercise 11: Dependency Injection

**Intent:** A class should not create its own dependencies; they should be
supplied ("injected") from outside. This decouples the class from concrete
implementations and makes it easy to test (inject a mock).

**Participants**
- Abstraction: `CustomerRepository` (`findCustomerById(id)`).
- Implementation: `CustomerRepositoryImpl` (in-memory map).
- Dependent: `CustomerService` — depends on `CustomerRepository`, received via
  **constructor injection** (not `new`ed internally).
- Composition root: `DITest` — creates the repository and injects it into the
  service.

Swapping the repository (e.g. to a database- or mock-backed one) requires no
change to `CustomerService`.

**Run**
```bash
javac DependencyInjectionExample/*.java
java  DependencyInjectionExample.DITest
```
