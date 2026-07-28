# orm-learn — HQL, Native Query & Criteria Query

Covers Hands-on 2–6: HQL with `join fetch`, aggregate functions, native queries,
a quiz object-graph fetch, and dynamic Criteria Query.

## Hands-on → code
| Hands-on | Topic | Where |
|---|---|---|
| 2 | HQL `join fetch` (department + skills in one query) | `EmployeeRepository.getAllPermanentEmployees` |
| 3 | Quiz graph via HQL fetch joins (user→attempt→…→options) | `AttemptRepository.getAttempt`, `model/quiz/*` |
| 4 | Aggregate `AVG` (all + by department, `:param`) | `EmployeeRepository.getAverageSalary(...)` |
| 5 | Native SQL query (`nativeQuery = true`) | `EmployeeRepository.getAllEmployeesNative` |
| 6 | Criteria Query (dynamic WHERE) | `ProductSearchService`, `ProductFilter` |

### Key points demonstrated
- **HQL addresses Java classes/fields**, not tables/columns.
- **`join` links, `fetch` populates** — associations are LAZY here, so the
  `left join fetch` in `getAllPermanentEmployees` is what loads department +
  skills in a single query (Hands-on 2's optimization).
- **Aggregates**: `SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id`.
- **Native query**: `SELECT * FROM employee` with `nativeQuery = true` — avoid
  when possible for DB portability.
- **Criteria Query**: `ProductSearchService` builds predicates only for the
  filters the user supplied (`CriteriaBuilder` → `CriteriaQuery` → `Root` →
  `TypedQuery`), which is ideal for the Amazon-style filter panel where the
  WHERE clause is not known ahead of time.

## Run against MySQL
```bash
mysql -u root -p ormlearn < sql/01_schema.sql
mysql -u root -p ormlearn < src/test/resources/data.sql   # sample data
mvn spring-boot:run
```

## Automated tests (H2, no MySQL needed)
```bash
mvn test
```
`OrmLearnHqlCriteriaTest` (7 tests) verifies all of the above against H2 seeded
from `src/test/resources/data.sql` — all pass, including:
- permanent employees loaded with department + skills (no `LazyInitializationException`),
- AVG salary overall (≈61666.67) and for dept 1 (67500),
- native query returns all 3 employees,
- the quiz attempt loads its full graph (4 questions, 14 options; `.html`
  selected and correct),
- Criteria filters (rating≥4.5 & RAM≥16 → 3 laptops; OS=Windows → 3 laptops).

## Notes
- **Quiz schema (`quiz.mwb`) wasn't provided**, so the schema/data are modeled to
  reproduce the exercise's expected display (4 HTML questions, per-option scores,
  the user's selected answers).
- Per the exercise's displayed data, **score is per option** (`o_score`) — two
  options of the last question show 0.5 — even though the prose says "score from
  the question table". The displayed data is treated as authoritative.
- `User` maps to table **`app_user`** (`user` is reserved). `options` entity is
  named `QuizOption`.
- Spring Boot 3.4.1 / Java 17 (`jakarta.persistence`).
