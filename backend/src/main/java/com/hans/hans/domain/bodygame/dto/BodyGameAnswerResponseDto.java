package com.hans.hans.domain.bodygame.dto;

import lombok.Builder;
import lombok.Getter;
import org.json.simple.JSONObject;

import java.util.Map;

@Getter
public class BodyGameAnswerResponseDto {

    private Long roomSequence;
    private JSONObject correctPlayers;
    private Long point;
    private String answer;

    @Builder
    public BodyGameAnswerResponseDto(Long roomSequence, JSONObject correctPlayers, Long point, String answer){
        this.roomSequence = roomSequence;
        this.correctPlayers = correctPlayers;
        this.point = point;
        this.answer = answer;
    }
}