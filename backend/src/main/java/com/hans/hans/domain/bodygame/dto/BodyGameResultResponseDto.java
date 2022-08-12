package com.hans.hans.domain.bodygame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.json.simple.JSONObject;

import java.util.Map;

@Getter
@AllArgsConstructor
public class BodyGameResultResponseDto {

    private JSONObject players;
}