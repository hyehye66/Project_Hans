package com.hans.hans.domain.bodygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BodyGameSubmitRequestDto {
    private String player;
    private String submit;
    @JsonProperty("problem_num")
    private int problemNum;
    private int time;
}
