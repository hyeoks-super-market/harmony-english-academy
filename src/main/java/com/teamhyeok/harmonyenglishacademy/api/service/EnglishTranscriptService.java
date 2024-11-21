package com.teamhyeok.harmonyenglishacademy.api.service;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import com.teamhyeok.harmonyenglishacademy.api.repository.EnglishTranscriptRepository;
import com.teamhyeok.harmonyenglishacademy.api.request.EnglishTranscriptCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor @Service @Slf4j
public class EnglishTranscriptService {
    private final EnglishTranscriptRepository englishTranscriptRepository;

    public void createTranscript(EnglishTranscriptCreate englishTranscriptCreate) {

        validateYouTubeVideo(englishTranscriptCreate.getYoutubeUrl());

        englishTranscriptRepository.save(englishTranscriptCreate.toEntity());

    }

    public List<EnglishTranscript> searchTranscriptByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EnglishTranscript> pageOfEnglishTranscript = englishTranscriptRepository.findByTitleContainingIgnoreCase(title, pageable);
        return pageOfEnglishTranscript.getContent();
    }

    @Cacheable(cacheNames = "getEnglishTranscript", key = "'englishTranscript:page:' + #page + ':size:' + #size", cacheManager = "englishTranscriptCacheManager")
    public List<EnglishTranscript> getEnglishTranscript(int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<EnglishTranscript> pageOfEnglishTranscript = englishTranscriptRepository.findAllByOrderByCreatedAtDesc(pageable);

        return pageOfEnglishTranscript.getContent();

    }

    private void validateYouTubeVideo(String youtubeUrl) {
        String apiKey = System.getenv("YOUTUBE_API_KEY");
        String videoId = extractVideoId(youtubeUrl);

        String apiUrl = "https://www.googleapis.com/youtube/v3/videos?id=" + videoId + "&key=" + apiKey + "&part=id";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        log.debug("youtube api 응답" + response);

        try {
            JSONObject json = new JSONObject(response.getBody());
            JSONArray items = json.getJSONArray("items");
            if (items.length() == 0) {
                throw new IllegalArgumentException("존재하지 않는 YouTube 비디오입니다.");
            }
        } catch (JSONException e) {
            throw new IllegalArgumentException("YouTube API 응답을 처리하는 중 오류가 발생했습니다.");
        }
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

