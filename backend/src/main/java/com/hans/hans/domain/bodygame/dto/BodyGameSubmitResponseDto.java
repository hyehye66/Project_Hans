package com.hans.hans.domain.bodygame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

@Getter
@AllArgsConstructor
public class BodyGameSubmitResponseDto {
    private JSONObject correctPlayers;

}
