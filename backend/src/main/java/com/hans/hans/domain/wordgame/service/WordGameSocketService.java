package com.hans.hans.domain.wordgame.service;


import com.hans.hans.domain.wordgame.dto.*;

public interface WordGameSocketService {
    WordGameStartResponseDto initGame(Long roomSequence, WordGameStartRequestDto wordGameStartRequestDto);
    WordGameProblemResponseDto getProblem(Long roomSequence, int problemNum);
    WordGameSubmitResponseDto submit(WordGameSubmitRequestDto wordGameSubmitRequestDto, Long roomSequence);
    WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence);
    WordGameResultResponseDto getResult(Long roomSequence);

}
