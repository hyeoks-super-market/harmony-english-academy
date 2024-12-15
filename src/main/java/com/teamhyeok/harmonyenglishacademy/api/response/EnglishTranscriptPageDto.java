package com.teamhyeok.harmonyenglishacademy.api.response;

import com.teamhyeok.harmonyenglishacademy.api.domain.EnglishTranscript;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class EnglishTranscriptPageDto {
    private List<EnglishTranscript> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;

    public static EnglishTranscriptPageDto fromPage(Page<EnglishTranscript> pageData) {
        return new EnglishTranscriptPageDto(
                pageData.getContent(),
                pageData.getNumber(),
                pageData.getSize(),
                pageData.getTotalElements()
        );
    }
}