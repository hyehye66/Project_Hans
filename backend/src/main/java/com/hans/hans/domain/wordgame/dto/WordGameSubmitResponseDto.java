package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class WordGameSubmitResponseDto {
    private List<String> players;
    private List<Long> points;

    @Builder
    public WordGameSubmitResponseDto(List<String> players, List<Long> points) {
        this.players = players;
        this.points = points;
    }

}
