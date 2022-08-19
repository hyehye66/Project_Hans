package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WordGameOwnerRequestDto {
    @JsonProperty("totalQuestion")
    private int totalQuestion;
}
