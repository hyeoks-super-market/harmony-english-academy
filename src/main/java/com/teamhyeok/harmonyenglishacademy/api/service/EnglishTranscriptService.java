package com.teamhyeok.harmonyenglishacademy.api.service;

import com.teamhyeok.harmonyenglishacademy.api.repository.EnglishTranscriptRepository;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor @Service
public class EnglishTranscriptService {
    private final EnglishTranscriptRepository englishTranscriptRepository;

    public void createTranscript(EnglishTranscriptCreate englishTranscriptCreate) {

        englishTranscriptRepository.save(englishTranscriptCreate.toEntity());

    }

}
