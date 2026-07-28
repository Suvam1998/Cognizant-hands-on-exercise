package com.example.accountservice;

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
class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAccount() throws Exception {
        mockMvc.perform(get("/accounts/00987987973432"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value("00987987973432"))
                .andExpect(jsonPath("$.type").value("savings"))
                .andExpect(jsonPath("$.balance").value(234343));
    }
}
