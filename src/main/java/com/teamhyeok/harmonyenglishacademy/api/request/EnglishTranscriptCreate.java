package com.teamhyeok.harmonyenglishacademy.api.request;


import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EnglishTranscriptCreate {

    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 제목이 필요합니다.")
    final private String title;

    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 내용이 필요합니다.")
    final private String content;

    @NotBlank(message = "YouTube URL은 필수 입력 사항입니다.")  // 빈 문자열인 경우 처리
    @Pattern(
            regexp = "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.be)/.+$",
            message = "유효한 YouTube URL을 입력해주세요."  // 형식이 잘못된 경우 처리
    )
    final private String youtubeUrl;

    public EnglishTranscript toEntity() {
        return EnglishTranscript.builder()
                .title(title)
                .content(content)
                .youtubeUrl(youtubeUrl)
                .build();
    }

    @Builder
    public EnglishTranscriptCreate(String title, String content, String youtubeUrl) {
        this.title = title;
        this.content = content;
        this.youtubeUrl = youtubeUrl;
    }
}
