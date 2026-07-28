# LibraryManagement — Spring Core + Maven (Exercises 1–8)

Classic (non-Boot) Spring project demonstrating XML configuration, IoC/DI, and AOP.

## Exercises covered
| # | Topic | Where |
|---|---|---|
| 1 | Basic Spring app + `applicationContext.xml` | `applicationContext.xml`, `BookService`, `BookRepository` |
| 2 | Dependency Injection (setter) | `BookService.setBookRepository`, XML wiring |
| 3 | Logging with Spring AOP (execution time) | `LoggingAspect` `@Around`, `aop:aspectj-autoproxy` |
| 4 | Maven project + deps (Context, AOP, WebMVC) + compiler plugin | `pom.xml` |
| 5 | Spring IoC container config | `applicationContext.xml` |
| 6 | Annotation-based beans (`@Service`, `@Repository`) | `applicationContext-annotation.xml` + annotations |
| 7 | Constructor **and** setter injection | `BookService` (both) + XML `constructor-arg` + `property` |
| 8 | Basic AOP (before/after advice) | `LoggingAspect` `@Before` / `@After` |

## Structure
```
src/main/java/com/library/
  LibraryManagementApplication.java   (main)
  service/BookService.java            (@Service, constructor + setter DI)
  repository/BookRepository.java      (@Repository)
  aspect/LoggingAspect.java           (@Aspect: before/after/around timing)
src/main/resources/
  applicationContext.xml              (XML beans + AOP)  -- Ex 1,2,3,5,7,8
  applicationContext-annotation.xml   (component scan)   -- Ex 6
```

## Run
```bash
mvn test          # runs LibraryContextTest (XML + annotation contexts)
mvn exec:java     # runs LibraryManagementApplication (loads applicationContext.xml)
```
Expected output includes AOP log lines and the injected result, e.g.:
```
Repository injected? true
[AOP] Timing  -> BookService.getBookDetails(..) executed in 22 ms
Result: [Central Library] Book#1 - 'Spring in Action'
```
To try the annotation-based config (Exercise 6), change the file name in
`LibraryManagementApplication` to `applicationContext-annotation.xml`.

## Note on versions
The exercise text specifies Java 1.8 and Spring Core (Spring 5). Spring 6
requires Java 17+, and the build/runtime JDK here is 24, so this project uses
**Java 17 + Spring 6.2.1** (the `jakarta`-based line). The concepts — XML config,
IoC/DI, AspectJ AOP — are identical.
