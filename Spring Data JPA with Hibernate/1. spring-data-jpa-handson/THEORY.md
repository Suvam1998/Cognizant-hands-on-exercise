# ORM, Hibernate & Spring Data JPA — Theory

Covers the objectives and the walkthrough hands-ons (2, 3, 4).

## 1. Why ORM? (Object-Relational Mapping)
ORM maps object-oriented domain classes to relational tables so you work with
**objects, not SQL result sets**.
- **Productivity** — no hand-written boilerplate JDBC/SQL for CRUD.
- **Abstraction** — the database system is abstracted; switching vendors is
  easier (database-independent queries via a dialect).
- **Transactionality & caching** — the ORM manages sessions, transactions, dirty
  checking, lazy loading, and caches.
- **Maintainability** — the persistence model lives in typed classes.

Trade-offs: a learning curve, possible performance surprises (N+1 queries), and
less control over generated SQL for complex cases.

## 2. Evolution of ORM in the Java world
1. **JDBC** — manual connections, statements, result-set mapping.
2. **Hibernate (XML config)** — `*.hbm.xml` mapping files + `hibernate.cfg.xml`.
3. **Hibernate (annotations) / JPA** — `@Entity`, `@Table`, `@Id`, `@Column` on
   the class; `hibernate.cfg.xml` or `persistence.xml`.
4. **Spring Data JPA** — a repository abstraction on top of a JPA provider
   (Hibernate) that removes boilerplate: you declare an interface and get CRUD +
   derived query methods for free.

## 3. Core objects of the Hibernate framework
- **Configuration** — reads config (`hibernate.cfg.xml`) and mappings; builds the
  SessionFactory.
- **SessionFactory** — heavyweight, thread-safe, **one per database**; a factory
  for `Session` objects.
- **Session** — a single-threaded, short-lived unit of work; wraps a JDBC
  connection; the main API for `save/get/delete/createQuery`.
- **Transaction** — an atomic unit of work (`beginTransaction`, `commit`,
  `rollback`); backed by a **TransactionFactory**.
- **ConnectionProvider** — abstracts the JDBC connection pool / DataSource.
- **TransactionFactory** — creates `Transaction` instances (usually internal).

## 4. Hands-on 2 — Hibernate **XML** configuration walkthrough
Reference: tutorialspoint `hibernate_examples`.
- **Persistence class** — a POJO (e.g. `Employee`) with fields + getters/setters.
- **Mapping file** `Employee.hbm.xml` — maps the class to a table and each
  property to a column (`<class name=... table=...>`, `<id>`, `<property>`).
- **Config file** `hibernate.cfg.xml` — dialect, driver, connection URL, user,
  password, and `<mapping resource="Employee.hbm.xml"/>`.
- **End-to-end operations** (in a DAO):
  ```
  SessionFactory factory = new Configuration().configure().buildSessionFactory();
  Session session = factory.openSession();
  Transaction tx = session.beginTransaction();
  Integer id = (Integer) session.save(employee);   // INSERT
  tx.commit();                                      // or tx.rollback() on error
  List employees = session.createQuery("FROM Employee").list();  // SELECT
  Employee e = session.get(Employee.class, id);     // by id
  session.delete(e);                                // DELETE
  session.close();
  ```

## 5. Hands-on 3 — Hibernate **annotation** configuration walkthrough
Reference: tutorialspoint `hibernate_annotations`.
- The mapping moves onto the `Employee` class via annotations — **no .hbm.xml**:
  - `@Entity` — marks the persistent class,
  - `@Table(name="EMPLOYEE")` — the mapped table,
  - `@Id` — the primary key,
  - `@GeneratedValue` — auto-generated key strategy,
  - `@Column(name="...")` — column mapping.
- `hibernate.cfg.xml` still supplies the connection settings:
  - **Dialect** (e.g. `org.hibernate.dialect.MySQLDialect`),
  - **Driver** (`com.mysql.cj.jdbc.Driver`),
  - **Connection URL**, **Username**, **Password**,
  - `<mapping class="com.example.Employee"/>`.
- Session/transaction usage is identical to the XML version.

## 6. Hands-on 4 — JPA vs Hibernate vs Spring Data JPA
| | What it is |
|---|---|
| **JPA** | A **specification** (JSR 338) for persisting/reading/managing data from Java objects. It has **no implementation** — only interfaces/annotations. |
| **Hibernate** | An **ORM tool** that **implements** JPA (a JPA provider). Adds features beyond the spec. |
| **Spring Data JPA** | An **abstraction above** a JPA provider (Hibernate). It does **not** implement JPA; it removes boilerplate — you declare a `JpaRepository` interface and get CRUD + query methods, and it manages transactions. |

Boilerplate comparison (add an employee):
```java
// Hibernate: open session, begin tx, save, commit/rollback, close — ~15 lines
Session session = factory.openSession();
Transaction tx = null;
try { tx = session.beginTransaction(); session.save(employee); tx.commit(); }
catch (HibernateException e) { if (tx != null) tx.rollback(); }
finally { session.close(); }

// Spring Data JPA: declare + call
interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
@Transactional void addEmployee(Employee e) { employeeRepository.save(e); }
```

## 7. `ddl-auto` behaviour (Hands-on 5)
`spring.jpa.hibernate.ddl-auto`:
- **create** — drop existing tables (data + structure), then create new ones.
- **validate** — check tables/columns exist; throw if a mapping doesn't match.
- **update** — create missing tables/columns; keep existing data.
- **create-drop** — create on startup, drop on shutdown.

This project uses **validate** against MySQL (schema pre-created via the `sql/`
scripts) and **create-drop** for the H2 tests.
