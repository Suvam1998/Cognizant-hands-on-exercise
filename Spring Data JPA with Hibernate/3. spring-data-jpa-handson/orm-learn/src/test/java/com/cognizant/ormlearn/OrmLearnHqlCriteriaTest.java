package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Product;
import com.cognizant.ormlearn.model.quiz.Attempt;
import com.cognizant.ormlearn.model.quiz.AttemptQuestion;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.ProductFilter;
import com.cognizant.ormlearn.service.ProductSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrmLearnHqlCriteriaTest {

    @Autowired private EmployeeService employeeService;
    @Autowired private AttemptService attemptService;
    @Autowired private ProductSearchService productSearchService;

    // ---------- Hands-on 2: HQL with join fetch ----------
    @Test
    void getAllPermanentEmployeesWithDepartmentAndSkills() {
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        assertThat(employees).hasSize(2);                    // John + Jane
        assertThat(employees).allMatch(Employee::isPermanent);
        // department + skills were fetched (no LazyInitializationException here)
        Employee john = employees.stream()
                .filter(e -> e.getName().equals("John Doe")).findFirst().orElseThrow();
        assertThat(john.getDepartment().getName()).isEqualTo("IT");
        assertThat(john.getSkillList()).hasSize(2);
    }

    // ---------- Hands-on 4: aggregate (AVG) ----------
    @Test
    void averageSalary() {
        // all: (60000 + 75000 + 50000) / 3 ≈ 61666.67
        assertThat(employeeService.getAverageSalary()).isCloseTo(61666.67, org.assertj.core.data.Offset.offset(0.1));
        // dept 1: (60000 + 75000) / 2 = 67500
        assertThat(employeeService.getAverageSalary(1)).isEqualTo(67500.0);
    }

    // ---------- Hands-on 5: native query ----------
    @Test
    void getAllEmployeesNative() {
        List<Employee> employees = employeeService.getAllEmployeesNative();
        assertThat(employees).hasSize(3);
    }

    // ---------- Hands-on 3: quiz HQL fetch ----------
    @Test
    void getAttemptFullGraph() {
        Attempt attempt = attemptService.getAttempt(1, 1);
        assertThat(attempt).isNotNull();
        assertThat(attempt.getUser().getName()).isEqualTo("alice");
        assertThat(attempt.getAttemptQuestions()).hasSize(4);

        int totalOptions = attempt.getAttemptQuestions().stream()
                .mapToInt(aq -> aq.getAttemptOptions().size()).sum();
        assertThat(totalOptions).isEqualTo(14);

        // Question 1: the selected option is ".html" and it is the correct one
        AttemptQuestion q1 = attempt.getAttemptQuestions().stream()
                .filter(aq -> aq.getQuestion().getId() == 1).findFirst().orElseThrow();
        boolean selectedHtmlCorrect = q1.getAttemptOptions().stream()
                .anyMatch(ao -> ao.isSelected()
                        && ao.getOption().getText().equals(".html")
                        && ao.getOption().isCorrect());
        assertThat(selectedHtmlCorrect).isTrue();
    }

    // ---------- Hands-on 6: Criteria Query dynamic filters ----------
    @Test
    void criteriaSearchNoFilters() {
        assertThat(productSearchService.search(new ProductFilter())).hasSize(5);
    }

    @Test
    void criteriaSearchByRatingAndRam() {
        ProductFilter filter = new ProductFilter();
        filter.setCategory("laptop");
        filter.setMinRating(4.5);
        filter.setMinRam(16);
        List<Product> results = productSearchService.search(filter);
        // Dell (16, 4.5), MacBook (16, 4.8), ThinkPad (32, 4.6)
        assertThat(results).extracting(Product::getName)
                .containsExactlyInAnyOrder("Dell XPS 13", "MacBook Pro", "Lenovo ThinkPad");
    }

    @Test
    void criteriaSearchByOs() {
        ProductFilter filter = new ProductFilter();
        filter.setOs("Windows");
        List<Product> results = productSearchService.search(filter);
        assertThat(results).extracting(Product::getName)
                .containsExactlyInAnyOrder("Dell XPS 13", "HP Pavilion", "Lenovo ThinkPad");
    }
}
