package com.teamhyeok.harmonyenglishacademy.api.controller;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import com.teamhyeok.harmonyenglishacademy.api.service.EnglishTranscriptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/english-transcript")
@RequiredArgsConstructor @RestController
public class EnglishTranscriptController {

    private final EnglishTranscriptService englishTranscriptService;
    @PostMapping
    public ResponseEntity<Map<String, String>> register(
            @RequestBody @Valid EnglishTranscriptCreate request
    ) {
        final URI REDIRECT_TO_MAIN_PAGE_URL = URI.create("/api/v1/english-transcripts");
        englishTranscriptService.createTranscript(request);

        return ResponseEntity.created(REDIRECT_TO_MAIN_PAGE_URL).build();
    }

    @GetMapping
    public ResponseEntity<List<EnglishTranscript>> getEnglishTranscript(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<EnglishTranscript> transcript = englishTranscriptService.getEnglishTranscript(page, size);
        return ResponseEntity.ok(transcript);

    }

    @GetMapping("/search")
    public ResponseEntity<List<EnglishTranscript>> searchTranscriptByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<EnglishTranscript> transcript = englishTranscriptService.searchTranscriptByTitle(title, page, size);
        return ResponseEntity.ok(transcript);
    }

}

