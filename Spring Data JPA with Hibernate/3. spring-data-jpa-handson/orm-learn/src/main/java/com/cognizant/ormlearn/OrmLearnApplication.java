package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static EmployeeService employeeService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");
        employeeService = context.getBean(EmployeeService.class);

        testGetAllPermanentEmployees();
        testGetAverageSalary();
    }

    // Hands-on 2
    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    // Hands-on 4
    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        LOGGER.debug("Average salary (all)={}", employeeService.getAverageSalary());
        LOGGER.debug("Average salary (dept 1)={}", employeeService.getAverageSalary(1));
        LOGGER.info("End");
    }
}
