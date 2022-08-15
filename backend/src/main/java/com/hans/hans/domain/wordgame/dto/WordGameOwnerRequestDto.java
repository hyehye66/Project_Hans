package com.hans.hans.domain.wordgame.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class WordGameOwnerRequestDto {
    private int totalQuestion;
}
