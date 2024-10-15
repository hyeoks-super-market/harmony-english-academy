package com.teamhyeok.harmonyenglishacademy.api.controller;

import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RequestMapping("/api/v1/english-transcript")
@RestController
public class EnglishTranscriptController {

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid EnglishTranscriptCreate request) {
        // TODO 1. 게시글 등록 실패시 400 Bad Request 반환
        final URI REDIRECT_TO_MAIN_PAGE_URL = URI.create("/api/v1/english-transcripts");


        return ResponseEntity.created(REDIRECT_TO_MAIN_PAGE_URL).build();
    }

}
