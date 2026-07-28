# Query Methods & O/R Mapping — Theory

## Query Methods (Spring Data JPA)
Spring Data derives a query from the **method name** — no SQL/JPQL needed. The
parser reads a subject (`findBy…`), property expressions, operators, and
ordering:

| Feature | Keyword | Example |
|---|---|---|
| Containing text | `Containing` | `findByNameContaining(String)` |
| Starts with | `StartingWith` | `findByNameStartingWith(String)` |
| Sorting | `OrderBy…Asc/Desc` | `findByNameContainingOrderByNameAsc(String)` |
| Between dates | `Between` | `findByCodeAndDateBetween(code, start, end)` |
| Greater / less than | `GreaterThan` / `LessThan` | `findByCodeAndCloseGreaterThan(code, price)` |
| Limit / top | `TopN` / `FirstN` | `findTop3ByOrderByVolumeDesc()` |
| Combine | `And` / `Or` | `findByCodeAndDateBetween(...)` |

Reference: Spring Data JPA — *Query Creation*.

## O/R Mapping — relationships
| Annotation | Meaning | Default fetch |
|---|---|---|
| `@ManyToOne` | many rows → one parent (Employee → Department) | **EAGER** |
| `@JoinColumn` | the FK column on the owning side (`em_dp_id`) | — |
| `@OneToMany(mappedBy=...)` | inverse of many-to-one (Department → Employees) | **LAZY** |
| `@ManyToMany` + `@JoinTable` | link table between two entities (Employee ↔ Skill) | **LAZY** |
| `mappedBy` | names the owning field on the other side (inverse side) | — |

### Fetch types
- **EAGER** — the association is loaded together with the owner (a join or a
  follow-up select). `@ManyToOne`/`@OneToOne` are EAGER by default — that's why
  fetching an Employee also fetches its Department.
- **LAZY** — the association loads on first access. `@OneToMany`/`@ManyToMany`
  are LAZY by default; accessing them **after the persistence session closes**
  throws `LazyInitializationException`. Hands-on 5 & 6 switch these to EAGER so
  the collections are available in the caller.

### Owning vs. inverse side
- The **owning side** holds the FK / join table and controls persistence of the
  link. Here `Employee` owns both relationships (it has `@JoinColumn` and
  `@JoinTable`).
- The **inverse side** uses `mappedBy` and is read-only for the association
  (`Department.employeeList`, `Skill.employeeList`). Changes must be made on the
  owning side to be persisted (e.g. add a Skill to `employee.getSkillList()`).

Reference: Baeldung — *Spring Data REST Relationships*.
