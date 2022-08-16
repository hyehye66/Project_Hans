package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class WordGameStartResponseDto {

    private List<String> players;
    private String gameStatus;
    private int totalQuestion;

    @Builder
    public WordGameStartResponseDto(List<String> players, String gameStatus, int totalQuestion){
        this.players = players;
        this.gameStatus = gameStatus;
        this.totalQuestion = totalQuestion;
    }

}
