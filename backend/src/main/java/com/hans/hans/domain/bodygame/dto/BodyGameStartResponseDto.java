package com.hans.hans.domain.bodygame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BodyGameStartResponseDto {

    private List<String> players;
    private String gameStatus;
}
