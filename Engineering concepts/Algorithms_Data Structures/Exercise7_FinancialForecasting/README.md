# Exercise 7: Financial Forecasting

## 1. Understand Recursive Algorithms
**Recursion** is when a method solves a problem by calling itself on a smaller
sub-problem, until it reaches a **base case** that is solved directly. It
simplifies problems that are naturally self-similar (factorials, tree/graph
traversal, divide-and-conquer, and compound-growth series like this one) by
expressing the solution in terms of a smaller instance of the same problem.

Every correct recursion needs:
1. a **base case** that stops the recursion, and
2. a **recursive case** that moves toward the base case.

Here, future value is defined by the recurrence:
```
futureValue(P, r, 0) = P                          // base case
futureValue(P, r, n) = futureValue(P, r, n-1) * (1 + r)
```
which is the closed form `P * (1 + r)^n`.

## 2 & 3. Setup + Implementation
`FinancialForecasting` provides:
- `futureValue` — naive recursion (constant growth rate),
- `futureValueVariable` — recursion over an array of per-year rates,
- `futureValueMemoized` — recursion + a cache,
- `futureValueIterative` — the iterative equivalent.

## 4. Analysis

### Time complexity
- **Naive recursion:** one call per year → **O(n) time, O(n) stack space**.
  (This particular recurrence is *linear* recursion — each call makes exactly one
  recursive call — so it is already O(n), not exponential.)
- A recurrence that recomputes overlapping sub-problems (e.g. naive Fibonacci,
  `f(n)=f(n-1)+f(n-2)`) would be **O(2ⁿ)** — that is the case memoization fixes.

### How to optimize / avoid excessive computation
| Technique | Benefit |
|---|---|
| **Memoization** (cache sub-results) | Turns repeated/overlapping calls from exponential into **O(n)**; repeat queries become O(1). Implemented in `futureValueMemoized`. |
| **Convert to iteration** | Removes call-stack overhead and the **StackOverflowError** risk for large `n`. Same O(n) time, but **O(1) space**. Implemented in `futureValueIterative`. |
| **Closed-form formula** | `P * Math.pow(1 + r, n)` computes the answer in **O(log n)** (fast exponentiation) or effectively O(1), with no recursion at all. |
| **Tail-call style** | Restructure so the recursive call is the last operation (though the JVM does not eliminate tail calls, so iteration is the practical fix in Java). |

**Bottom line:** recursion gives the clearest expression of the growth
recurrence, but for production forecasting use the closed-form `pow` (fastest) or
the iterative loop (safe for large horizons); add memoization when the same
sub-results are queried repeatedly.

## How to run
```bash
javac Exercise7_FinancialForecasting/*.java
java Exercise7_FinancialForecasting.FinancialForecasting
```
