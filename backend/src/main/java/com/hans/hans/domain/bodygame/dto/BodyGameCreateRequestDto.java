package com.hans.hans.domain.bodygame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class BodyGameCreateRequestDto {
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "제한인원은 필수 입력값입니다.")
    @JsonProperty("restrict_num")
    private int restrictNum;

    @NotNull(message = "문제수는 필수 입력값입니다.")
    @JsonProperty("problem_num")
    private int problemNum;

    @NotNull(message = "난이도는 필수 입력값입니다.")
    private int difficulty;

    @NotNull(message = "제한시간은 필수 입력값입니다.")
    @JsonProperty("time_limit")
    private int timeLimit;
}
