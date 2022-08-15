package com.hans.hans.domain.bodygame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BodyGameOwnerRequestDto {
    private Long difficulty;
    private int timeLimit;
    private int totalQuestion;
}
