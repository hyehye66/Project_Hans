package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WordGameSubmitRequestDto {
    private String player;
    private String submit;
    @JsonProperty("problem_num")
    private int problemNum;
}
