package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WordGameStartRequestDto {
    @JsonProperty("total_question")
    int totalQuestion;

}
