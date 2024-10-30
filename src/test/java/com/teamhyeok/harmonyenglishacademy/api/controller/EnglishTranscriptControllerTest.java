package com.teamhyeok.harmonyenglishacademy.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamhyeok.harmonyenglishacademy.api.repository.EnglishTranscriptRepository;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc @SpringBootTest
class EnglishTranscriptControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EnglishTranscriptRepository englishTranscriptRepository;

    @DisplayName("게시글 등록 요청시 IS_CREATED 헤더가 내려온다.")
    @Test
    void register() throws Exception{
        // given
        EnglishTranscriptCreate request = EnglishTranscriptCreate.builder()
                                            .title("The Glee, season2 ep.3")
                                            .content("the series ...@#!#")
                                            .build();

        String stringRequest = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/api/v1/english-transcript")
                        .contentType(APPLICATION_JSON)
                        .content(stringRequest)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/english-transcripts"))
                .andDo(print());

    }


    @DisplayName("게시글 등록 요청시 제목은 필수로 기입되어야 한다.")
    @Test
    void register_title_is_required() throws Exception {

        // given
        EnglishTranscriptCreate request = EnglishTranscriptCreate.builder()
                .title("")
                .content("the series ...@#!#")
                .build();

        String stringRequest = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/api/v1/english-transcript")
                        .contentType(APPLICATION_JSON)
                        .content(stringRequest)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("영어 스크립트 게시글 등록을 위해선 이름이 필요합니다."))
                .andDo(print());

    }

    @DisplayName("/api/v1/english-transcript에 POST 요청 이후 해당 게시글이 DB를 통해 정상적으로 조회된다.")
    @Test
    void register_save_transcript() throws Exception {

        // given
        String uuidTitle = "Glee season1 (ep1) - " + UUID.randomUUID();
        EnglishTranscriptCreate request = EnglishTranscriptCreate.builder()
                .title(uuidTitle)
                .content("the series ...@#!#")
                .build();

        String stringRequest = objectMapper.writeValueAsString(request);


        // when
        mockMvc.perform(post("/api/v1/english-transcript")
                        .contentType(APPLICATION_JSON)
                        .content(stringRequest)
                )
                .andExpect(status().isCreated())
                .andDo(print());

        // then - 등록된 게시글 제목으로 조회
        mockMvc.perform(get("/api/v1/english-transcript/search")
                        .param("title", uuidTitle)
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(uuidTitle))
                .andDo(print());
    }

}