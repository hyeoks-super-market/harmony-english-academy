package com.teamhyeok.harmonyenglishacademy.api.controller;

import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/english-transcript")
@RestController
public class EnglishTranscriptController {

    @PostMapping
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid EnglishTranscriptCreate request, BindingResult result) {
        // TODO 1. 게시글 등록 실패시 400 Bad Request 반환
        final URI REDIRECT_TO_MAIN_PAGE_URL = URI.create("/api/v1/english-transcripts");

        // TODO 2. Title validation check (type2)
        /* EnglishTranscriptCreate에 valid 애노테이션 적용하는 방법 */

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put("field", fieldName);
            error.put("errorMessage", errorMessage);

            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.created(REDIRECT_TO_MAIN_PAGE_URL).build();
    }

}
