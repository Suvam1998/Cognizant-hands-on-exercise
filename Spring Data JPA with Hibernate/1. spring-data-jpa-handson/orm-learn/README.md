# orm-learn — Spring Data JPA with Hibernate

Cognizant "orm-learn" hands-on: manage `Country` data through Spring Data JPA.
Covers Hands-on 1 (setup) and Hands-on 5–9 (find-by-code, add, update, delete,
partial-name search). Hands-on 2–4 are theory (see [THEORY.md](../THEORY.md)).

## Structure
```
src/main/java/com/cognizant/ormlearn/
  OrmLearnApplication.java                 (main + test methods)
  model/Country.java                       (@Entity, @Table, @Id, @Column)
  repository/CountryRepository.java        (JpaRepository + findByNameContaining)
  service/CountryService.java              (@Service, @Transactional CRUD)
  service/exception/CountryNotFoundException.java
src/main/resources/application.properties  (MySQL config, per the exercise)
sql/01_create_schema.sql                   (MySQL schema + country table)
sql/02_country_data.sql                    (all ~240 countries, MySQL)
src/test/...                               (H2-based automated verification)
```

## Run against MySQL (as in the exercise)
1. Create schema + table and load data:
   ```bash
   mysql -u root -p < sql/01_create_schema.sql
   mysql -u root -p ormlearn < sql/02_country_data.sql
   ```
2. Set your credentials in `src/main/resources/application.properties`
   (`spring.datasource.username` / `password`).
3. Run the app:
   ```bash
   mvn spring-boot:run
   ```
   `OrmLearnApplication.main` logs "Inside main" and runs the Hands-on test
   methods (get all, find by code, add, update, delete, partial-name search)
   with SQL trace logging enabled.

## Automated tests (no MySQL needed)
```bash
mvn test
```
`CountryServiceTest` runs against an **H2** in-memory DB seeded from
`src/test/resources/data.sql` (the same country list) and verifies every
Hands-on 5–9 feature, including the `CountryNotFoundException` path. 5 tests pass.

## Hands-on → code map
| Hands-on | Feature | Code |
|---|---|---|
| 1 | Project setup + `main` logging | `OrmLearnApplication` |
| 5 | Partial-name search + data | `findCountriesByPartialName`, `sql/02_country_data.sql` |
| 6 | Find by code (+ exception) | `findCountryByCode`, `CountryNotFoundException` |
| 7 | Add country | `addCountry` → `save()` |
| 8 | Update country | `updateCountry` → `findById` + `save()` |
| 9 | Delete country | `deleteCountry` → `deleteById()` |

## Notes
- The table columns are **`co_code` / `co_name`** (per Hands-on 5's data script),
  so `Country` maps `@Column(name="co_code")` / `@Column(name="co_name")`.
  (The exercise's inline snippet showed `code`/`name`; the data script columns
  are authoritative.)
- Uses **Spring Boot 3.4.1 + Java 17** (jakarta.persistence), the current
  equivalent of the exercise's Spring Boot / `javax.persistence` setup;
  `MySQL5Dialect` (deprecated in Hibernate 6) is replaced by `MySQLDialect`.
