# orm-learn — Query Methods & O/R Mapping (Spring Data JPA)

Extends the Country example with **Query Methods** (country + stock) and
**relationship mapping** (Employee / Department / Skill).

## Entities
| Entity | Table | Notable mapping |
|---|---|---|
| `Country` | country | `@Id co_code`, `co_name` |
| `Stock` | stock | `@GeneratedValue` id, code/date/open/close/volume |
| `Employee` | employee | `@ManyToOne Department` (`em_dp_id`); `@ManyToMany Set<Skill>` via `employee_skill` |
| `Department` | department | `@OneToMany(mappedBy="department", fetch=EAGER) Set<Employee>` |
| `Skill` | skill | `@ManyToMany(mappedBy="skillList") Set<Employee>` |

## Hands-on → Query Methods
**Hands-on 1 — country** (`CountryRepository`):
- `findByNameContaining(text)` — search box ("ou" → Bouvet Island, Djibouti, …).
- `findByNameContainingOrderByNameAsc(text)` — same, sorted ascending.
- `findByNameStartingWith(prefix)` — alphabet index ("Z" → Zambia, Zimbabwe).

**Hands-on 2 — stock** (`StockRepository`):
- `findByCodeAndDateBetween(code, start, end)` — FB in September 2019 (19 rows).
- `findByCodeAndCloseGreaterThan(code, price)` — GOOGL close > 1250 (7 rows).
- `findTop3ByOrderByVolumeDesc()` — top 3 highest-volume days.
- `findTop3ByCodeOrderByCloseAsc(code)` — NFLX 3 lowest closes.

## Hands-on → Relationships
| Hands-on | Mapping | Verified by |
|---|---|---|
| 4 | `@ManyToOne` Employee→Department (EAGER by default) | `employeeHasDepartment` |
| 5 | `@OneToMany` Department→Employees (EAGER to avoid `LazyInitializationException`) | `departmentHasEmployees` |
| 6 | `@ManyToMany` Employee↔Skill via `employee_skill`; add skill | `employeeHasSkills`, `addSkillToEmployee` |

## Run against MySQL
```bash
mysql -u root -p ormlearn < sql/01_schema.sql
mysql -u root -p ormlearn < sql/02_data.sql      # stock + payroll sample data
# (load the country list from ../1. spring-data-jpa-handson/orm-learn/sql/02_country_data.sql too)
mvn spring-boot:run
```

## Automated tests (H2, no MySQL needed)
```bash
mvn test
```
`OrmLearnQueryAndMappingTest` (11 tests) runs against H2 seeded from
`src/test/resources/data.sql` (countries + curated stock + payroll) and verifies
every query method and relationship — all pass.

## Notes
- **Stock/payroll sample data is curated** here (the exercise's `stock-data.csv`
  and `payroll.sql` files were not provided). The stock rows reproduce exactly
  the values in the exercise's expected outputs (FB Sept 2019, GOOGL > 1250,
  top-3 volume, NFLX lows) plus a few extras so the filters are meaningful.
- The H2 test URL uses `IGNORECASE=TRUE` so string matching is case-insensitive,
  matching **MySQL's default `utf8_general_ci` collation** that the exercise's
  expected "contains" output assumes (e.g. "ou" matching "Outlying").
- `@OneToMany` / `@ManyToMany` are set to `FetchType.EAGER` per Hands-on 5 & 6
  (the default LAZY throws `LazyInitializationException` outside a session).
- Spring Boot 3.4.1 / Java 17 (jakarta.persistence).
