package com.teamhyeok.harmonyenglishacademy.api.response;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EnglishTranscriptPageDto {
    private List<EnglishTranscript> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
}
