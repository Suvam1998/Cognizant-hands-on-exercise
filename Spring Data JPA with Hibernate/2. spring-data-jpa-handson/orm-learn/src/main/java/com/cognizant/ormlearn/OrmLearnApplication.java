package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static StockService stockService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        stockService = context.getBean(StockService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        // Uncomment the demo you want to run (each hits the configured database).
        // testSearchCountry();
        // testTopVolumeStocks();
        testGetEmployee();
    }

    // ---- Hands-on 1: country query methods ----
    @SuppressWarnings("unused")
    private static void testSearchCountry() {
        LOGGER.info("Start");
        LOGGER.debug("Containing 'ou'={}", countryService.searchByName("ou"));
        LOGGER.debug("Containing 'ou' sorted={}", countryService.searchByNameSorted("ou"));
        LOGGER.debug("Starting 'Z'={}", countryService.searchByStartingLetter("Z"));
        LOGGER.info("End");
    }

    // ---- Hands-on 2: stock query methods ----
    @SuppressWarnings("unused")
    private static void testTopVolumeStocks() {
        LOGGER.info("Start");
        LOGGER.debug("Top 3 by volume={}", stockService.getTop3ByVolume());
        LOGGER.info("End");
    }

    // ---- Hands-on 4/6: get employee with department + skills ----
    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }
}
