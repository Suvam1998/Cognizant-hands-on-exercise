# spring-learn — RESTful Web Services (Spring Boot)

Adds REST endpoints to the spring-learn app and tests them with MockMVC.

## Endpoints
| Method | URL | Controller method | Response |
|---|---|---|---|
| GET | `/hello` | `HelloController.sayHello` | `Hello World!!` |
| GET | `/country` | `CountryController.getCountryIndia` | `{"code":"IN","name":"India"}` |
| GET | `/countries` | `CountryController.getAllCountries` | array of 4 countries |
| GET | `/country/{code}` (also `/countries/{code}`) | `CountryController.getCountry` | country by code (case-insensitive) |

- `CountryService.getCountry(code)` loads the list from `country.xml` and matches
  the code **case-insensitively** (lambda + `equalsIgnoreCase`).
- Unknown code → `CountryNotFoundException`
  (`@ResponseStatus(HttpStatus.NOT_FOUND, reason = "Country not found")`) → HTTP **404**.

## Run
```bash
mvn spring-boot:run        # http://localhost:8083
```
Try (browser / Postman / curl):
```bash
curl -i http://localhost:8083/hello
curl -i http://localhost:8083/country
curl -i http://localhost:8083/countries
curl -i http://localhost:8083/country/jp     # case-insensitive
curl -i http://localhost:8083/country/az     # 404 Country not found
```
Verified live output:
```
/hello      -> Hello World!!
/country    -> {"code":"IN","name":"India"}
/countries  -> [{IN India},{US United States},{DE Germany},{JP Japan}]
/country/jp -> {"code":"JP","name":"Japan"}
/country/az -> HTTP 404
```

## MockMVC tests
```bash
mvn test
```
`SpringLearnApplicationTests` (6 tests) — `@SpringBootTest` + `@AutoConfigureMockMvc`:
- `contextLoads` — `CountryController` is autowired/loaded,
- `/hello` returns the greeting,
- `/country` → 200 with `$.code` = "IN", `$.name` = "India" (`jsonPath`),
- `/countries` → array of 4,
- `/country/in` → case-insensitive match,
- `/country/az` → `status().isNotFound()` + `status().reason("Country not found")`.

## Notes
- **How the bean becomes JSON:** the `@RestController` return value is serialized
  by Spring's `MappingJackson2HttpMessageConverter` (Jackson) into
  `application/json`, driven by the request's `Accept` header via the
  DispatcherServlet.
- **Exercise inconsistency:** the MockMVC exception snippet shows
  `status().isBadRequest()` / reason `"Country Not found"`, which contradicts the
  `CountryNotFoundException` definition (`NOT_FOUND` / `"Country not found"`). The
  test follows the exception class (404), which is what the app actually returns.
- Spring Boot 3.4.1 / Java 17; `server.port=8083`.
