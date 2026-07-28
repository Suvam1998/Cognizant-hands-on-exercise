# spring-learn — REST POST/PUT/DELETE with Validation

Adds create/update/delete services with Bean Validation and a global exception
handler, following REST resource-naming guidelines.

## Endpoints (resource-per-URL)
| Method | URL | Purpose |
|---|---|---|
| GET | `/countries` | all countries |
| GET | `/countries/{code}` | one country (404 if missing) |
| POST | `/countries` | create (payload `@Valid`) |
| PUT | `/countries` | update (payload `@Valid`) |
| DELETE | `/countries/{code}` | delete |
| GET | `/employees` | all employees |
| PUT | `/employees` | update employee (payload `@Valid`, 404 if id missing) |
| DELETE | `/employees/{id}` | delete employee (404 if id missing) |

`CountryController` uses class-level `@RequestMapping("/countries")`; the same
URL serves all HTTP methods.

## Validation (jakarta.validation + Hibernate Validator)
- **Country.code**: `@NotNull` + `@Size(min=2,max=2, message="Country code should be 2 characters")`.
- **Employee**: `id @NotNull`, `name @NotBlank @Size(1,30)`, `salary @NotNull @Min(0)`,
  `permanent @NotNull`, `dateOfBirth @JsonFormat(pattern="dd/MM/yyyy")`,
  `department @NotNull @Valid`, `skillList @Valid`.
- **Department / Skill**: `id @NotNull`, `name @NotBlank @Size(1,30)`.
- Controllers use `@RequestBody @Valid`.

## Global exception handling (`GlobalExceptionHandler`)
`@ControllerAdvice` extending `ResponseEntityExceptionHandler`:
- `handleMethodArgumentNotValid` — `@Valid` failures → `{timestamp,status,errors[]}` (400).
- `handleHttpMessageNotReadable` — wrong type in a numeric field (JSON parse
  error) → `{timestamp,status,error,message:"Incorrect format for field 'id'"}` (400).

## Verified live (curl)
```
POST /countries {"code":"IN","name":"India"}  -> {"code":"IN","name":"India"}
POST /countries {"code":"I",...}              -> 400 {"errors":["Country code should be 2 characters"]}
PUT  /employees {"id":"abc",...}              -> 400 {"message":"Incorrect format for field 'id'"}
PUT  /employees {"id":99,...}                 -> 404 (Employee not found)
DELETE /employees/2  (then again)             -> 200, then 404
```

## Tests
```bash
mvn test
```
`SpringLearnApplicationTests` (7 tests): valid/invalid country POST, valid
employee update, not-found update (404), bad number format (400), validation
error (400), and delete (200 then 404).

## Notes
- **jakarta**, not `javax`: Spring Boot 3 uses `jakarta.validation.*` and the
  `@Valid`/global-handler flow replaces the exercise's manual
  `ValidatorFactory`/`ResponseStatusException` approach (the disadvantage the
  exercise calls out — repeating validation code per controller — is solved by
  the single `@ControllerAdvice`).
- **Spring 6 signatures**: the overridden handler methods take `HttpStatusCode`
  (not `HttpStatus`) — the exercise's snippet predates this change.
- `EmployeeNotFoundException` / `CountryNotFoundException` use
  `@ResponseStatus(NOT_FOUND, reason=...)`.
- Spring Boot 3.4.1 / Java 17, `server.port=8083`.
