package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.wordgame.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.wordgame.repository.WordGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameSocketServiceImpl implements WordGameSocketService {
    private final WordGameRoom wordGameRoom;
    Map<Long, WordGameRoom> wordGameMap = new HashMap<>();
    private final WordGameRepository wordGameRepository;
    private final WordGameRoomService wordGameRoomService;

    @Override
    public WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto){
        WordGameStartResponseDto wordGameStartResponseDto = wordGameRoom.initWordGame();
        wordGameMap.put(roomSequence, wordGameRoom);

        return wordGameStartResponseDto;
    }

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

    @Override
    public WordGameResultResponseDto getResult(Long roomSequence){
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        Map<String,Integer> players = wordGameRoom.getPlayers();

        List<String> playersKeySet = new ArrayList<>(players.keySet());

        playersKeySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return players.get(o1).compareTo(players.get(o2));
            }
        });

        Map<String,Integer> result = new HashMap<>();

        for(String key: playersKeySet){
            result.put(key,players.get(key));
        }

        WordGameResultResponseDto wordGameResultResponseDto = new WordGameResultResponseDto(result);

        return wordGameResultResponseDto;
    }
}
