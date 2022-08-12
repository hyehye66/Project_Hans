package com.hans.hans.domain.bodygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BodyGameStartRequestDto {
    @JsonProperty("total_question")
    @NotBlank(message = "총 문제수는 필수 입력값입니다.")
    private int totalQuestion;
    @NotBlank(message = "난이도는 필수 입력값입니다.")
    private Long difficulty;
}
