package com.digitalnurture.inventory;

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

@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void upsertThenCheckAvailability() throws Exception {
        mockMvc.perform(post("/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productId\":1,\"quantityAvailable\":8}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/inventory/1/available").param("quantity", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inStock").value(8))
                .andExpect(jsonPath("$.available").value(true));

        mockMvc.perform(get("/inventory/1/available").param("quantity", "20"))
                .andExpect(jsonPath("$.available").value(false));
    }
}
