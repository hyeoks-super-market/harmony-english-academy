package com.teamhyeok.harmonyenglishacademy.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class EnglishTranscriptControllerTest {

    @Autowired
    MockMvc mockMvc;


    @DisplayName("게시글 등록 요청시 OK 헤더가 내려온다.")
    @Test
    void register() throws Exception{

        // expected
        mockMvc.perform(post("/api/v1/english-transcript")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"The Glee, season2 ep.3\", \"content\": \"the series ...@#!#\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/english-transcripts"))
                .andDo(print());

    }


    @DisplayName("게시글 등록 요청시 제목은 필수로 기입되어야 한다.")
    @Test
    void register_title_is_required() throws Exception {

        // expected
        mockMvc.perform(post("/api/v1/english-transcript")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"the series ...@#!#\"}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());

    }

}