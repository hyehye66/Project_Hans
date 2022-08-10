package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class WordGameResultResponseDto {

    private Map<String,Integer> players;
}
