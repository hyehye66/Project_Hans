package com.hans.hans.domain.wordgame.service;


import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameResultResponseDto;

public interface WordGameService {
    WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence);
    WordGameResultResponseDto getResult(Long roomSequence);
}
