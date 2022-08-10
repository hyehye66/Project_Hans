package com.hans.hans.domain.wordgame.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class WordGameAnswerResponseDto {

    private Long roomSequence;
    private Map<String, Long> correctPlayers;
    private int point;
    private String answer;

    @Builder
    public WordGameAnswerResponseDto(Long roomSequence, Map<String, Long> correctPlayers, int point, String answer){
        this.roomSequence = roomSequence;
        this.correctPlayers = correctPlayers;
        this.point = point;
        this.answer = answer;
    }
}