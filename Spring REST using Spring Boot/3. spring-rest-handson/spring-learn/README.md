# spring-learn — Employee & Department REST (Controller → Service → Dao)

GET REST services that return employee and department data loaded from Spring
XML configuration, following the Controller / Service / Dao architecture. This is
the backend the Angular employee-list and edit-employee screens consume.

## Architecture
```
EmployeeController  ->  EmployeeService (@Service, @Transactional)  ->  EmployeeDao (@Repository)
   /employees                getAllEmployees()                         EMPLOYEE_LIST (from employee.xml)

DepartmentController ->  DepartmentService (@Service, @Transactional) -> DepartmentDao (@Repository)
   /departments              getAllDepartments()                        DEPARTMENT_LIST (from employee.xml)
```

## Data (employee.xml)
- 3 skills (Java, Python, SQL) — reused across employees
- 3 departments (IT, HR, Finance) → `departmentList`
- 4 employees (John, Jane, Bob, Alice) with department + skill refs → `employeeList`

Each DAO holds a **static list** (`EMPLOYEE_LIST` / `DEPARTMENT_LIST`) populated
from `employee.xml` in its constructor.

## Endpoints
| Method | URL | Returns |
|---|---|---|
| GET | `/employees` | array of 4 employees (each with nested `department` + `skillList`) |
| GET | `/departments` | array of 3 departments |

## Run
```bash
mvn spring-boot:run        # http://localhost:8083
```
```bash
curl -s http://localhost:8083/employees
curl -s http://localhost:8083/departments
```
Verified live:
```
/employees   -> [{"id":1,"name":"John",...,"department":{"id":1,"name":"IT"},"skillList":[Java,SQL]}, ...4 total]
/departments -> [{"id":1,"name":"IT"},{"id":2,"name":"HR"},{"id":3,"name":"Finance"}]
```
The console shows START/END logs across Controller → Service → Dao for each call
(the department service call is logged too, as required).

## Tests
```bash
mvn test
```
`SpringLearnApplicationTests` (3 tests): `contextLoads` (both controllers loaded),
`/employees` (4 items, nested department "IT" and skill "Java"), `/departments`
(3 items).

## Notes
- **CORS** is opened for `http://localhost:4200` (`AppConfig`) so the Angular dev
  server can consume these services.
- `@Transactional` needs a transaction manager; since this app has no real
  database, `spring-boot-starter-jdbc` + embedded **H2** is included purely so
  Spring Boot auto-configures a `DataSourceTransactionManager` (the H2 DB itself
  is unused — data comes from XML).
- Spring Boot 3.4.1 / Java 17, `server.port=8083`, package `com.cognizant.springlearn`.
