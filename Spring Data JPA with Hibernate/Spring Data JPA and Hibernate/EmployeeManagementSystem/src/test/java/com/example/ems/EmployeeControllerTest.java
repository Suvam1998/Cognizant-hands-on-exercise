package com.example.ems;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Exercise 4 (CRUD) + Exercise 6 (pagination) through the REST API. */
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createDepartmentEmployeeThenPaginate() throws Exception {
        // create a department
        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Engineering\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        // create an employee in that department
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Dave\",\"email\":\"dave@corp.com\",\"department\":{\"id\":1}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dave"));

        // paginated endpoint returns a Spring Data Page structure
        mockMvc.perform(get("/employees/page").param("size", "5").param("sortBy", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalElements").exists());
    }
}
