package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WordGameOwnerResponseDto {
    private String owner;
    private int totalQuestion;
}
