package com.hans.hans.domain.bodygame.dto;

import com.hans.hans.domain.word.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BodyGameProblemResponseDto {
    private String problem;

    public BodyGameProblemResponseDto(Word word){
        this.problem = word.getWord();
    }
}
