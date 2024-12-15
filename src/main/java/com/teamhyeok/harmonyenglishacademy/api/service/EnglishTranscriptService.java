package com.teamhyeok.harmonyenglishacademy.api.service;

import com.teamhyeok.harmonyenglishacademy.api.client.YouTubeClient;
import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import com.teamhyeok.harmonyenglishacademy.api.repository.EnglishTranscriptRepository;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import com.teamhyeok.harmonyenglishacademy.api.response.EnglishTranscriptPageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class EnglishTranscriptService {
    private final EnglishTranscriptRepository englishTranscriptRepository;
    private final YouTubeClient youTubeClient;

    public void createTranscript(EnglishTranscriptCreate englishTranscriptCreate) {

        validateYouTubeVideo(englishTranscriptCreate.getYoutubeUrl());

        englishTranscriptRepository.save(englishTranscriptCreate.toEntity());

    }

    @Cacheable(cacheNames = "getEnglishTranscriptDto", key = "'englishTranscript:page:' + #page + ':size:' + #size", cacheManager = "englishTranscriptCacheManager")
    public EnglishTranscriptPageDto getEnglishTranscriptDto(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EnglishTranscript> pageData = englishTranscriptRepository.findAllByOrderByCreatedAtDesc(pageable);
        return EnglishTranscriptPageDto.fromPage(pageData);
    }

    public List<EnglishTranscript> searchTranscriptByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EnglishTranscript> pageOfEnglishTranscript = englishTranscriptRepository.findByTitleContainingIgnoreCase(title, pageable);
        return pageOfEnglishTranscript.getContent();
    }

    private void validateYouTubeVideo(String youtubeUrl) {
        String videoId = extractVideoId(youtubeUrl);
        youTubeClient.validateVideoExistence(videoId);
    }

    private String extractVideoId(String youtubeUrl) {
        String regex = "(?<=v=|youtu\\.be/|embed/)[^&\\n?#]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(youtubeUrl);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new IllegalArgumentException("유효한 YouTube URL이 아닙니다.");
        }
    }

}

