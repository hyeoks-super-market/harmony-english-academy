package com.teamhyeok.harmonyenglishacademy.api.controller;

import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
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
    public ResponseEntity<Map<String, String>> register(@RequestBody EnglishTranscriptCreate request) {
        // TODO 1. 게시글 등록 실패시 400 Bad Request 반환
        final URI REDIRECT_TO_MAIN_PAGE_URL = URI.create("/api/v1/english-transcripts");


        // TODO 1. Title validation check (type1)

        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getContent() == null | request.getContent().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (request.getTitle().length() < 2 || request.getTitle().length() > 100) {
            return ResponseEntity.badRequest().build();
        } // .. 등등 직접 컨트롤러 안에서 유효성 검증 처리 구현, 벌써 복잡해진다.


        return ResponseEntity.created(REDIRECT_TO_MAIN_PAGE_URL).build();
    }

}
