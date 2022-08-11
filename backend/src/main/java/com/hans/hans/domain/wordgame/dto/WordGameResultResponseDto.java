package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class WordGameResultResponseDto {

    List<String> players;
    List<Long> points;
}
