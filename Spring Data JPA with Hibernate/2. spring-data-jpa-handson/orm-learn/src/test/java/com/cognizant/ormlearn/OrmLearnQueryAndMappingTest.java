package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrmLearnQueryAndMappingTest {

    @Autowired private CountryService countryService;
    @Autowired private StockService stockService;
    @Autowired private EmployeeService employeeService;
    @Autowired private DepartmentService departmentService;
    @Autowired private SkillService skillService;

    // ---------------- Hands-on 1: country query methods ----------------

    @Test
    void searchByContainingText() {
        List<Country> countries = countryService.searchByName("ou");
        assertThat(countries).extracting(Country::getName).contains(
                "Bouvet Island", "Djibouti", "Guadeloupe",
                "South Georgia and the South Sandwich Islands", "Luxembourg",
                "South Sudan", "French Southern Territories",
                "United States Minor Outlying Islands", "South Africa");
    }

    @Test
    void searchByContainingTextSorted() {
        List<Country> countries = countryService.searchByNameSorted("ou");
        List<String> names = countries.stream().map(Country::getName).toList();
        assertThat(names).isSorted();
        assertThat(names.get(0)).isEqualTo("Bouvet Island");
    }

    @Test
    void searchByStartingLetter() {
        List<Country> countries = countryService.searchByStartingLetter("Z");
        assertThat(countries).extracting(Country::getName)
                .containsExactlyInAnyOrder("Zambia", "Zimbabwe");
    }

    // ---------------- Hands-on 2: stock query methods ----------------

    @Test
    void facebookSeptember2019() {
        List<Stock> stocks = stockService.getStocksBetween(
                "FB", Date.valueOf("2019-09-01"), Date.valueOf("2019-09-30"));
        assertThat(stocks).hasSize(19);
        assertThat(stocks).allMatch(s -> s.getCode().equals("FB"));
    }

    @Test
    void googleCloseGreaterThan1250() {
        List<Stock> stocks = stockService.getStocksWithCloseGreaterThan("GOOGL", 1250);
        assertThat(stocks).hasSize(7);
        assertThat(stocks).allMatch(s -> s.getClose() > 1250);
    }

    @Test
    void top3ByVolume() {
        List<Stock> stocks = stockService.getTop3ByVolume();
        assertThat(stocks).hasSize(3);
        assertThat(stocks.get(0).getVolume()).isEqualTo(77233600L);
        assertThat(stocks).isSortedAccordingTo(
                (a, b) -> Long.compare(b.getVolume(), a.getVolume()));
    }

    @Test
    void netflixThreeLowest() {
        List<Stock> stocks = stockService.getLowest3ByClose("NFLX");
        assertThat(stocks).hasSize(3);
        assertThat(stocks.get(0).getClose()).isEqualTo(233.88);
        assertThat(stocks).isSortedAccordingTo(
                (a, b) -> Double.compare(a.getClose(), b.getClose()));
    }

    // ---------------- Hands-on 4: @ManyToOne ----------------

    @Test
    void employeeHasDepartment() {
        Employee employee = employeeService.get(1);
        assertThat(employee.getDepartment()).isNotNull();
        assertThat(employee.getDepartment().getName()).isEqualTo("IT");
    }

    // ---------------- Hands-on 5: @OneToMany (EAGER) ----------------

    @Test
    void departmentHasEmployees() {
        Department department = departmentService.get(1);
        assertThat(department.getEmployeeList()).hasSize(2);
        assertThat(department.getEmployeeList()).extracting(Employee::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Smith");
    }

    // ---------------- Hands-on 6: @ManyToMany ----------------

    @Test
    void employeeHasSkills() {
        Employee employee = employeeService.get(1);
        assertThat(employee.getSkillList()).extracting(Skill::getName)
                .containsExactlyInAnyOrder("Java", "SQL");
    }

    @Test
    void addSkillToEmployee() {
        // Employee 1 initially has Java + SQL (not Python)
        Employee employee = employeeService.get(1);
        Skill python = skillService.get(2);
        employee.getSkillList().add(python);
        employeeService.save(employee);

        Employee reloaded = employeeService.get(1);
        assertThat(reloaded.getSkillList()).extracting(Skill::getName)
                .contains("Java", "SQL", "Python");
    }
}
