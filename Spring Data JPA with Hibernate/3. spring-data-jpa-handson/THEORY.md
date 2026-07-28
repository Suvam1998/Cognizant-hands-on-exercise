# HQL / JPQL, Native Query & Criteria Query — Theory

## HQL vs JPQL
- **HQL** = Hibernate Query Language. **JPQL** = Java Persistence Query Language.
- Both are **object-oriented** query languages (they address **entities and
  their fields**, not tables and columns) and resemble SQL.
- **JPQL is a subset of HQL** — every JPQL query is valid HQL, but not the
  reverse. Both support `SELECT`, `UPDATE`, `DELETE`; **HQL additionally allows
  `INSERT`** (`INSERT … SELECT`).
- In Spring Data JPA both are written with the **`@Query`** annotation; native
  SQL is enabled with **`nativeQuery = true`**.

## `join` vs `join fetch` (the key takeaway)
- `join` **links** tables for filtering, but does **not populate** the associated
  beans.
- `join fetch` **populates** the associated beans in the same query.
- Associations default to LAZY (`@OneToMany`, `@ManyToMany`) or EAGER
  (`@ManyToOne`, `@OneToOne`). With LAZY associations, use `join fetch` where you
  actually need the data, turning N+1 selects into **one** query. Omitting fetch
  where data isn't needed keeps the query lean.

## Aggregate functions
HQL supports `COUNT`, `SUM`, `AVG`, `MIN`, `MAX`, `GROUP BY`, `HAVING`:
```jpql
SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id
```
- Navigate associations with dot notation (`e.department.id`).
- Bind parameters with `:name` + `@Param("name")`.

## Native queries
- Direct SQL to the database (`nativeQuery = true`).
- Use sparingly — they tie you to a specific database dialect and hurt
  portability. Prefer HQL/JPQL, which Hibernate translates per dialect.

## Criteria Query — the need
When the WHERE clause is **not known at compile time** (e.g. an e-commerce filter
panel: category, customer review, RAM, CPU speed, OS, weight — user picks any
subset), building one fixed HQL string is awkward and error-prone.

**Criteria API** builds the query **programmatically and type-safely**:
| Object | Role |
|---|---|
| `CriteriaBuilder` | factory for queries, predicates, expressions |
| `CriteriaQuery<T>` | the query definition (select/where/order) |
| `Root<T>` | the FROM entity; `root.get("field")` references a column |
| `Predicate` | a condition; combine with `cb.and(...)` / `cb.or(...)` |
| `TypedQuery<T>` | the executable, typed query (`em.createQuery(cq)`) |

You add a `Predicate` **only for each filter the user actually chose**, then
combine them — exactly the dynamic-WHERE problem Criteria Query solves
(see `ProductSearchService`).

References: Hibernate Dev Guide ch.11; Baeldung *Spring Data JPA Query*;
Oracle Java EE tutorial *Using the Criteria API*.
