package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WordGameAnswerRequestDto {

    @JsonProperty("question_num")
    private int questionmNum;
}