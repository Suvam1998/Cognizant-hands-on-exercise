# Exercise 6: Proxy Pattern

**Intent:** Provide a surrogate/placeholder for another object to control access
to it — here, a **virtual proxy** adding lazy initialization and caching.

**Participants**
- Subject: `Image` (interface, `display()`).
- Real subject: `RealImage` — expensive; "loads" from a remote server on
  construction.
- Proxy: `ProxyImage` — creates the `RealImage` only on the first `display()`
  (lazy load) and reuses it on later calls (caching).

**Run**
```bash
javac ProxyPatternExample/*.java
java  ProxyPatternExample.ProxyTest
```
