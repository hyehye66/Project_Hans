package com.hans.hans.domain.wordgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;

@Getter
@AllArgsConstructor
public class WordGameSubmitResponseDto {
    private Set<String> correctPlayers;

}
