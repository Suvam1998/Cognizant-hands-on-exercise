# spring-learn — Spring Boot & Spring Core (XML config)

Cognizant "spring-learn" hands-on: create a Spring Boot app and load beans from
Spring XML configuration (SimpleDateFormat, Country, scopes, list).

## Hands-on → implementation
| Hands-on | Topic | Where |
|---|---|---|
| 1 | Spring Web project + `main()` logging | `SpringLearnApplication` (`@SpringBootApplication`, `SpringApplication.run`, "Inside main") |
| 2 | Load `SimpleDateFormat` from XML | `date-format.xml` + `displayDate()` (parses `31/12/2018`) |
| 3 | Logging | `application.properties` (`logging.level`, `logging.pattern.console`), SLF4J `Logger` everywhere (no `System.out.println`) |
| 4 | Load `Country` bean from XML | `Country.java` (debug logs in ctor/getters/setters), `country.xml`, `displayCountry()` |
| 5 | Singleton vs prototype scope | `country` (singleton) & `countryPrototype` (`scope="prototype"`) in `country.xml` |
| 6 | Load list of countries | `countryList` (`ArrayList` of `<ref>`s) + `displayCountries()` |

## Run
```bash
mvn spring-boot:run
```
The console shows (custom log pattern):
```
...|INFO |...SpringLearnApplication|main            |Inside main
...|INFO |...SpringLearnApplication|displayDate     |START
...|DEBUG|...SpringLearnApplication|displayDate     |Date: Mon Dec 31 00:00:00 ... 2018
...|DEBUG|...Country               |<init>          |Inside Country Constructor.
...|DEBUG|...SpringLearnApplication|displayCountry  |Country : Country [code=IN, name=India]
...|DEBUG|...SpringLearnApplication|displayCountry  |Same instance (singleton)? true
...|DEBUG|...SpringLearnApplication|displayCountries|Country List: [IN India, US United States, DE Germany, JP Japan]
```

## Tests
```bash
mvn test
```
`SpringLearnXmlTest` (4 tests) verifies: the date-format bean parses the date;
the country bean has code/name; **singleton returns the same instance while
prototype returns different instances**; and the country list has all 4 entries
in order.

## Key concepts (SME walkthrough)
- **`<bean id=... class=...>`** — declares a bean; `id` is its name, `class` the
  implementation. **`<property name= value=>`** = setter injection;
  **`<constructor-arg value=>`** = constructor injection.
- **`ApplicationContext` / `ClassPathXmlApplicationContext`** — the IoC container;
  loads the XML and manages bean lifecycles.
- **`context.getBean(id, Type.class)`** — returns the bean; for **singleton**
  (default) the same instance each call, for **prototype** a new instance each
  call (visible via the constructor debug log firing once vs. multiple times).
- **`<list>` / `<ref bean=>`** — inject a collection of references to other beans.

## Notes
- Spring Boot 3.4.1 / Java 17. `server.port=8083` avoids an 8080 conflict.
- `main()` starts the embedded Tomcat (Spring Web) and then runs the three
  `display*` demo methods, mirroring the exercise's "run as Java Application".
