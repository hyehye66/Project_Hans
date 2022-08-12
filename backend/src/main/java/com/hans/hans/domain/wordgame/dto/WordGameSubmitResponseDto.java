package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WordGameSubmitResponseDto {
    private List<String> correctPlayers;

}
