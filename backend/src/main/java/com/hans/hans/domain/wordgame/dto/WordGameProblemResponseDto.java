package com.hans.hans.domain.wordgame.dto;

import com.hans.hans.domain.word.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WordGameProblemResponseDto {
    private String problem;

    public WordGameProblemResponseDto(Word word){
        this.problem = word.getMean();
    }
}
