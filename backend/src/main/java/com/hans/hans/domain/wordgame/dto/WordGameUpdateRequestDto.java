package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class WordGameUpdateRequestDto {
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @JsonProperty("restrict_num")
    private int restrictNum;

    @JsonProperty("problem_num")
    private int problemNum;
}
