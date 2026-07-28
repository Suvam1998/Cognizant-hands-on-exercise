# LibraryManagement — Spring Boot (Exercise 9)

Spring Boot version of the library app: REST CRUD over a `Book` entity backed by
Spring Data JPA and an H2 in-memory database.

## Requirements covered
- Spring Web + Spring Data JPA + H2.
- `Book` entity, `BookRepository extends JpaRepository`.
- `BookController` with full CRUD (`GET`, `POST`, `PUT`, `DELETE`).
- DB config in `application.properties`.

## Structure
```
src/main/java/com/library/
  LibraryManagementApplication.java
  model/Book.java
  repository/BookRepository.java
  controller/BookController.java
src/main/resources/application.properties
```

## Run
```bash
mvn spring-boot:run          # starts on :8080
```
H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:librarydb`).

## Test the REST endpoints
```bash
# Create
curl -X POST localhost:8080/books -H "Content-Type: application/json" \
     -d '{"title":"Clean Code","author":"Robert Martin"}'
# List
curl localhost:8080/books
# Get one
curl localhost:8080/books/1
# Update
curl -X PUT localhost:8080/books/1 -H "Content-Type: application/json" \
     -d '{"title":"Clean Code (2nd)","author":"Robert C. Martin"}'
# Delete
curl -X DELETE localhost:8080/books/1
```

## Tests
```bash
mvn test    # BookControllerTest: create+fetch, and 404 for unknown id
```

Spring Boot 3.4.1 / Java 17 (verified on JDK 24).
