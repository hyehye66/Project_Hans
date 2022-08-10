package com.hans.hans.domain.wordgame.service;


import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameStartRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameStartResponseDto;

public interface WordGameSocketService {
    WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence);
    WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto);

}
