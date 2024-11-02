package com.teamhyeok.harmonyenglishacademy.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class StatusControllerTest {

    @Autowired
    MockMvc mockMvc;

    @DisplayName("health-check 요청시 OK 헤더가 내려온다.")
    @Test
    void checkApiStatus() throws Exception {
        mockMvc.perform(get("/api/v1/health-check"))
                .andExpect(status().isOk())
                .andDo(print());

    }

}