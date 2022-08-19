package com.hans.hans.domain.bodygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BodyGameAnswerRequestDto {

    @JsonProperty("question_num")
    private int questionNum;
}