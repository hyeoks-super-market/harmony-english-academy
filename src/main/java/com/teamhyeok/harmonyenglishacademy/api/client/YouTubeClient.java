package com.teamhyeok.harmonyenglishacademy.api.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class YouTubeClient {
    @Value("${youtube.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate(); // Bean 주입해 사용 가능

    public void validateVideoExistence(String videoId) {
        String apiUrl = String.format("https://www.googleapis.com/youtube/v3/videos?id=%s&key=%s&part=id",
                videoId, apiKey);
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        log.debug("YouTube API Response: {}", response);

        try {
            JSONObject json = new JSONObject(response.getBody());
            JSONArray items = json.getJSONArray("items");
            if (items.length() == 0) {
                throw new IllegalArgumentException("존재하지 않는 YouTube 비디오입니다.");
            }
        } catch (JSONException e) {
            throw new IllegalArgumentException("YouTube API 응답 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
