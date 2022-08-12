package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WordGameStartResponseDto {

    private List<String> players;
    private String gameStatus;

}
