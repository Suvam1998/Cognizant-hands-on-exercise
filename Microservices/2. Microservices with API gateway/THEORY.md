# Theory — Enterprise Applications, Monolith vs. Microservices

## Enterprise Application
Systems that manage the service levels of large organizations (e.g. a bank's
Savings Account, Loans, Insurance, Cards, Net Banking, Phone/SMS Banking,
Micro Finance…) are called **Enterprise Applications**. A single business
operation — say *"get account balance"* — is consumed by many channels and
roles: mobile app, bank teller, net banking, IVR, customer service reps, batch
EMI jobs, ATMs, SMS systems, and so on.

## The Monolith problem (the 4 PM incident)
A bank's operations were all built as RESTful services packaged into **one**
deployable (a single EAR/WAR) — a **monolithic service**. During festival-season
load, a **memory leak** in the high-traffic *get-balance* code exhausted memory.
Because everything ran in one process, **unrelated features went down too**:
loan agents couldn't submit applications, insurance closures stalled, and
customers waited in queues. The only remedy was a full server restart, with a
2–3 hour recovery — and no certainty it wouldn't recur.

### Drawbacks of monolithic services
- One service's performance/memory-leak issue **brings down all services**.
- **Single point of failure** across unrelated domains.
- Cannot adopt a **different technology stack** per service.
- Harder to scale, deploy, and reason about as the codebase grows.

## Microservices
Split the monolith into **multiple independent services**, each owning one
capability (e.g. accounts, loans, insurance) and running in its own process.

### Advantages
- **Decentralized** and **independent** — a failure in *get-balance* no longer
  takes down loans/insurance (no single point of failure).
- **Does one thing well**; **agility** in developing each service.
- **Scalable** — add instances of just the hot service on new hardware without
  touching the rest of production.
- **Easier fault isolation**; easier onboarding; enables **continuous delivery**.

### Challenges
- Distributed systems are inherently **complex**.
- **Initial implementation** (infrastructure, discovery, gateway, config) is
  harder than a monolith.

## How this maps to the hands-on modules here
| Concept | Module |
|---|---|
| Independent services doing one thing well | `account`, `loan`, `greet-service` |
| Service discovery registry | `eureka-discovery-server` |
| Single entry point + cross-cutting logging | `api-gateway` (global `LogFilter`) |

Splitting *account* and *loan* into separate Spring Boot apps (each with its own
`pom.xml`, running on different ports — 8080 / 8081) is exactly the monolith→
microservices decomposition described above.
