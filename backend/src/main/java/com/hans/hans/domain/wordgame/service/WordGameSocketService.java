package com.hans.hans.domain.wordgame.service;


import com.hans.hans.domain.wordgame.dto.*;

public interface WordGameSocketService {
    WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto);
    WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence);
    WordGameResultResponseDto getResult(Long roomSequence);

}
