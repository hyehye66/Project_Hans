package com.hans.hans.domain.bodygame.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BodyGameOwnerResponseDto {
    private String owner;
    private Long difficulty;
    private int timeLimit;
    private int totalQuestion;

    @Builder
    public BodyGameOwnerResponseDto(String owner, Long difficulty, int timeLimit, int totalQuestion){
        this.owner = owner;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.totalQuestion = totalQuestion;
    }
}
