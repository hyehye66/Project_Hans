package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartResponseDto;

public interface BodyGameSocketService {
    public BodyGameStartResponseDto initGame(long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto);
}
