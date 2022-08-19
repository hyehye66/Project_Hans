package com.hans.hans.domain.conversation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ConversationUpdateRequestDto {
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @JsonProperty("restrict_num")
    private int restrictNum;
}
