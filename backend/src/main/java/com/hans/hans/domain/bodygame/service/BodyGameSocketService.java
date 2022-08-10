package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameProblemResponseDto;

public interface BodyGameSocketService {

    public BodyGameStartResponseDto initGame(long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto);
    BodyGameProblemResponseDto getProblem(Long wordSequence);


}
