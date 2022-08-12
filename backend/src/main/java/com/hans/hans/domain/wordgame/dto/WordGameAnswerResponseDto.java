package com.hans.hans.domain.wordgame.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public class WordGameAnswerResponseDto {

    private Long roomSequence;
    private Set<String> correctPlayers;
    private Long point;
    private String answer;

    @Builder
    public WordGameAnswerResponseDto(Long roomSequence, Set<String> correctPlayers, Long point, String answer){
        this.roomSequence = roomSequence;
        this.correctPlayers = correctPlayers;
        this.point = point;
        this.answer = answer;
    }
}