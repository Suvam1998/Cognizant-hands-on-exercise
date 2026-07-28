package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mvc;

    // ----- Country POST validation -----
    @Test
    void addCountryValid() throws Exception {
        mvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"IN\",\"name\":\"India\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("IN"))
                .andExpect(jsonPath("$.name").value("India"));
    }

    @Test
    void addCountryInvalidCode() throws Exception {
        // code "I" is 1 char -> @Size fails -> global handler returns 400 + errors
        mvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\":\"I\",\"name\":\"India\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasItem("Country code should be 2 characters")));
    }

    // ----- Employee PUT (update) -----
    private static final String VALID_EMPLOYEE = "{"
            + "\"id\":1,\"name\":\"John Updated\",\"salary\":55000,\"permanent\":true,"
            + "\"dateOfBirth\":\"15/05/1990\","
            + "\"department\":{\"id\":1,\"name\":\"IT\"},"
            + "\"skillList\":[{\"id\":1,\"name\":\"Java\"}]}";

    @Test
    void updateEmployeeValid() throws Exception {
        mvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_EMPLOYEE))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployeeNotFound() throws Exception {
        String json = VALID_EMPLOYEE.replace("\"id\":1", "\"id\":99");
        mvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEmployeeInvalidNumberFormat() throws Exception {
        // id as a string -> JSON parse error -> handleHttpMessageNotReadable -> 400
        String json = VALID_EMPLOYEE.replace("\"id\":1", "\"id\":\"abc\"");
        mvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Incorrect format for field 'id'"));
    }

    @Test
    void updateEmployeeValidationError() throws Exception {
        // blank name -> @NotBlank fails -> 400 with errors
        String json = VALID_EMPLOYEE.replace("\"name\":\"John Updated\"", "\"name\":\"\"");
        mvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    // ----- Employee DELETE -----
    @Test
    void deleteEmployee() throws Exception {
        // first delete succeeds, second (already removed) -> 404
        mvc.perform(delete("/employees/4")).andExpect(status().isOk());
        mvc.perform(delete("/employees/4")).andExpect(status().isNotFound());
    }
}
