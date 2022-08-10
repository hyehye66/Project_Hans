package com.hans.hans.domain.wordgame.service;


import com.hans.hans.domain.wordgame.dto.*;

public interface WordGameSocketService {
    WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto);
    WordGameProblemResponseDto getProblem(Long wordSequence);
    WordGameSubmitResponseDto checkAnswer(WordGameSubmitRequestDto wordGameSubmitRequestDto, Long wordSequence, Long roomSequence);
    WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence);
    WordGameResultResponseDto getResult(Long roomSequence);

}
