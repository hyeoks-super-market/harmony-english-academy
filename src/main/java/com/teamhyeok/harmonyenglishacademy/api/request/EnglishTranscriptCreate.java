package com.teamhyeok.harmonyenglishacademy.api.request;


import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EnglishTranscriptCreate {

    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 이름이 필요합니다.")
    private String title;

    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 내용이 필요합니다.")
    private String content;

    public EnglishTranscript toEntity() {

        return EnglishTranscript.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public EnglishTranscriptCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
