package com.hans.hans.domain.bodygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BodyGameStartRequestDto {
    @JsonProperty("total_question")
    private int totalQuestion;
    private Long difficulty;
    private int timeLimit;
}
