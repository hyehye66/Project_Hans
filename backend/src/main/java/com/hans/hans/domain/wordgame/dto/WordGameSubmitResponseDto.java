package com.hans.hans.domain.wordgame.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WordGameSubmitResponseDto {

    private String submit;
    private boolean isAnswer;

    @Builder
    public WordGameSubmitResponseDto(String submit, boolean isAnswer) {
        this.submit = submit;
        this.isAnswer = isAnswer;
    }

}
