package com.hans.hans.domain.bodygame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BodyGameStartResponseDto {

    private List<String> players;
    private String gameStatus;
    private int timeLimit;
    private int totalQuestion;

    @Builder
    public BodyGameStartResponseDto(List<String> players, String gameStatus, int timeLimit, int totalQuestion){
        this.players = players;
        this.gameStatus = gameStatus;
        this.timeLimit = timeLimit;
        this.totalQuestion = totalQuestion;
    }
}
