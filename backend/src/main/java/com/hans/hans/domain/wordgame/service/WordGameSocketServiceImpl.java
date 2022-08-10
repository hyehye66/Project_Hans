package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameStartRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameStartResponseDto;
import com.hans.hans.domain.wordgame.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.wordgame.repository.WordGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WordGameSocketServiceImpl implements WordGameSocketService {
    private final WordGameRoom wordGameRoom;
    Map<Long, WordGameRoom> wordGameMap = new HashMap<>();
    private final WordGameRepository wordGameRepository;
    private final WordGameRoomService wordGameRoomService;
    @Override
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence) {
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);

        Word word = wordGameRepository.findByWordSequence(wordGameRoom.getWordsSequence().get(wordGameAnswerRequestDto.getQuestionmNum()));

        WordGameAnswerResponseDto wordGameAnswerResponseDto =
                WordGameAnswerResponseDto.builder()
                        .correctPlayers(wordGameRoom.getCorrectPlayers())
                        .roomSequence(wordGameRoom.getRoomSequence())
                        .answer(word.getWord())
                        .point(word.getDifficulty())
                        .build();

        return wordGameAnswerResponseDto;
    }

    public WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto){
        WordGameStartResponseDto wordGameStartResponseDto = wordGameRoom.initWordGame();
        wordGameMap.put(roomSequence, wordGameRoom);

        return wordGameStartResponseDto;
    }
}
