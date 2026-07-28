package com.cognizant.springlearn;

import com.cognizant.springlearn.controller.DepartmentController;
import com.cognizant.springlearn.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired private EmployeeController employeeController;
    @Autowired private DepartmentController departmentController;
    @Autowired private MockMvc mvc;

    @Test
    void contextLoads() {
        assertNotNull(employeeController);
        assertNotNull(departmentController);
    }

    @Test
    void getAllEmployees() throws Exception {
        mvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].department.name").value("IT"))
                .andExpect(jsonPath("$[0].skillList[0].name").value("Java"));
    }

    @Test
    void getAllDepartments() throws Exception {
        mvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].name").value("IT"));
    }
}
