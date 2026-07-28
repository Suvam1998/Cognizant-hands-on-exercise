package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    static ArrayList<Employee> EMPLOYEE_LIST;

    @SuppressWarnings("unchecked")
    public EmployeeDao() {
        LOGGER.info("Inside EmployeeDao constructor - loading employee list from employee.xml");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList");
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }

    /** Update an employee in the list; throw if the id is not found. */
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("START");
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(i, employee);
                LOGGER.debug("Updated employee: {}", employee);
                LOGGER.info("END");
                return;
            }
        }
        throw new EmployeeNotFoundException("Employee not found for id: " + employee.getId());
    }

    /** Delete an employee by id; throw if the id is not found. */
    public void deleteEmployee(int id) throws EmployeeNotFoundException {
        LOGGER.info("START");
        boolean removed = EMPLOYEE_LIST.removeIf(e -> e.getId().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Employee not found for id: " + id);
        }
        LOGGER.debug("Deleted employee id: {}", id);
        LOGGER.info("END");
    }
}
