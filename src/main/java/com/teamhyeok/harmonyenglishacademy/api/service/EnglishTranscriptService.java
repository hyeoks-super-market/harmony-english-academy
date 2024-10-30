package com.teamhyeok.harmonyenglishacademy.api.service;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import com.teamhyeok.harmonyenglishacademy.api.repository.EnglishTranscriptRepository;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor @Service
public class EnglishTranscriptService {
    private final EnglishTranscriptRepository englishTranscriptRepository;

    public void createTranscript(EnglishTranscriptCreate englishTranscriptCreate) {

        englishTranscriptRepository.save(englishTranscriptCreate.toEntity());

    }

    public List<EnglishTranscript> searchTranscriptByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EnglishTranscript> pageOfEnglishTranscript = englishTranscriptRepository.findByTitleContainingIgnoreCase(title, pageable);
        return pageOfEnglishTranscript.getContent();
    }
}

