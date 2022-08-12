package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.*;

public interface BodyGameSocketService {

    BodyGameStartResponseDto initGame(long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto);
    BodyGameProblemResponseDto getProblem(Long roomSequence, int problemNum);
    BodyGameSubmitResponseDto submit(BodyGameSubmitRequestDto bodyGameSubmitRequestDto, Long roomSequence);
    BodyGameAnswerResponseDto getAnswer(BodyGameAnswerRequestDto bodyGameAnswerRequestDto, Long roomSequence);
    BodyGameResultResponseDto getResult(Long roomSequence);
}
