package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Hands-on 2: HQL with "join fetch" so department + skills load in ONE query.
    // (HQL addresses the Java classes/fields, not tables/columns.)
    @Query("SELECT DISTINCT e FROM Employee e "
            + "LEFT JOIN FETCH e.department "
            + "LEFT JOIN FETCH e.skillList "
            + "WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // Hands-on 4: aggregate function AVG over all employees.
    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    // Hands-on 4: AVG filtered by department id (note ':id' bound via @Param).
    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // Hands-on 5: Native SQL query (nativeQuery = true).
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
