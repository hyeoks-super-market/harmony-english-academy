package com.teamhyeok.harmonyenglishacademy.api.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EnglishTranscriptCreate {


    // TODO 2. request validation check (type2)
    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 이름이 필요합니다.")
    private String title;

    @NotBlank(message = "영어 스크립트 게시글 등록을 위해선 내용이 필요합니다.")
    private String content;

    /* valid annotation 적용해서 유효성 검증을 진행하는 방법 */




}
