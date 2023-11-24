package com.decathlonapplication.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
        import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.MediaType;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class DecathlonControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testCalculatePointsEndpoint() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/calculatePoints/100M/10.0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.event").value("100M"))
                    .andExpect(jsonPath("$.result").value(10.0))
                    .andExpect(jsonPath("$.points").isNumber());
        }
    }



