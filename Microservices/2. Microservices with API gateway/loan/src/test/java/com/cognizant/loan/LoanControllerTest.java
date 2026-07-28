package com.cognizant.loan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLoanByNumber() throws Exception {
        mockMvc.perform(get("/loans/H00987987972342"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("H00987987972342"))
                .andExpect(jsonPath("$.type").value("car"))
                .andExpect(jsonPath("$.emi").value(3258));
    }
}
