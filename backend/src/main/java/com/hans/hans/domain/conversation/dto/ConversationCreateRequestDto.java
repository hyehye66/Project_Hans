package com.hans.hans.domain.conversation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ConversationCreateRequestDto {
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "제한인원은 필수 입력값입니다.")
    @JsonProperty("restrict_num")
    private int restrictNum;
}
