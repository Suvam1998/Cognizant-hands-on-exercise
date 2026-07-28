package com.example.ems;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.projection.EmployeeDto;
import com.example.ems.projection.EmployeeView;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private DepartmentRepository departmentRepository;

    private Long deptId;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        Department it = departmentRepository.save(new Department("IT"));
        Department hr = departmentRepository.save(new Department("HR"));
        deptId = it.getId();

        employeeRepository.save(new Employee("Alice", "alice@corp.com", it));
        employeeRepository.save(new Employee("Bob", "bob@corp.com", it));
        employeeRepository.save(new Employee("Carol", "carol@other.com", hr));
    }

    // Exercise 5: derived queries
    @Test
    void derivedQueries() {
        assertThat(employeeRepository.findByName("Alice")).hasSize(1);
        assertThat(employeeRepository.findByEmailContaining("corp.com")).hasSize(2);
        assertThat(employeeRepository.findByDepartmentName("IT")).hasSize(2);
    }

    // Exercise 5: @Query
    @Test
    void customQuery() {
        assertThat(employeeRepository.searchByDepartment("HR")).hasSize(1);
    }

    // Exercise 5: named query
    @Test
    void namedQuery() {
        assertThat(employeeRepository.findByEmailDomain("%@corp.com")).hasSize(2);
    }

    // Exercise 6: pagination + sorting
    @Test
    void paginationAndSorting() {
        Page<Employee> page = employeeRepository.findAll(
                PageRequest.of(0, 2, Sort.by("name").ascending()));
        assertThat(page.getTotalElements()).isEqualTo(3);
        assertThat(page.getContent()).hasSize(2);
        assertThat(page.getContent().get(0).getName()).isEqualTo("Alice");
        assertThat(page.getTotalPages()).isEqualTo(2);
    }

    // Exercise 8: interface projection
    @Test
    void interfaceProjection() {
        List<EmployeeView> views = employeeRepository.findByDepartment_Id(deptId);
        assertThat(views).hasSize(2);
        assertThat(views).extracting(EmployeeView::getName)
                .containsExactlyInAnyOrder("Alice", "Bob");
        assertThat(views.get(0).getDisplay()).contains("<");
    }

    // Exercise 8: class-based (DTO) projection
    @Test
    void dtoProjection() {
        List<EmployeeDto> dtos = employeeRepository.findAllProjectedDto();
        assertThat(dtos).hasSize(3);
        assertThat(dtos).extracting(EmployeeDto::getEmail).contains("alice@corp.com");
    }

    // Exercise 7: auditing populates created/modified metadata
    @Test
    void auditingPopulatesTimestamps() {
        Employee e = employeeRepository.findByName("Alice").get(0);
        assertThat(e.getCreatedDate()).isNotNull();
        assertThat(e.getCreatedBy()).isEqualTo("system");
        assertThat(e.getLastModifiedDate()).isNotNull();
    }

    // Exercise 10: batch save
    @Test
    void batchSave() {
        Department it = departmentRepository.findByName("IT").orElseThrow();
        List<Employee> batch = List.of(
                new Employee("D1", "d1@corp.com", it),
                new Employee("D2", "d2@corp.com", it),
                new Employee("D3", "d3@corp.com", it));
        employeeRepository.saveAll(batch);
        assertThat(employeeRepository.count()).isEqualTo(6);
    }
}
