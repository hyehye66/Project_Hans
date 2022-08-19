package com.hans.hans.domain.wordgame.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class WordGameAnswerResponseDto {

    private Long roomSequence;
    private List<String> correctPlayers;
    private Long point;
    private String answer;

    @Builder
    public WordGameAnswerResponseDto(Long roomSequence, List<String> correctPlayers, Long point, String answer){
        this.roomSequence = roomSequence;
        this.correctPlayers = correctPlayers;
        this.point = point;
        this.answer = answer;
    }
}