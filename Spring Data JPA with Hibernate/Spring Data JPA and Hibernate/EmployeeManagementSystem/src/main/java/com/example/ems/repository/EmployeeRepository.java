package com.example.ems.repository;

import com.example.ems.entity.Employee;
import com.example.ems.projection.EmployeeDto;
import com.example.ems.projection.EmployeeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Exercise 5: derived query methods (keywords in the method name)
    List<Employee> findByName(String name);

    List<Employee> findByEmailContaining(String text);

    List<Employee> findByDepartmentName(String departmentName);

    // Exercise 5: custom query with @Query (JPQL)
    @Query("SELECT e FROM Employee e WHERE e.department.name = :dept")
    List<Employee> searchByDepartment(@Param("dept") String dept);

    // Exercise 5: Named query (resolves to @NamedQuery "Employee.findByEmailDomain")
    List<Employee> findByEmailDomain(@Param("domain") String domain);

    // Exercise 6: pagination + sorting
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    // Exercise 8: interface-based projection
    List<EmployeeView> findByDepartment_Id(Long departmentId);

    // Exercise 8: class-based (DTO) projection via constructor expression
    @Query("SELECT new com.example.ems.projection.EmployeeDto(e.name, e.email) FROM Employee e")
    List<EmployeeDto> findAllProjectedDto();
}
