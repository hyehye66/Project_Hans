package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

@Getter
@AllArgsConstructor
public class WordGameResultResponseDto {

    private JSONObject players;
}
