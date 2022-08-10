package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameResultResponseDto;
import com.hans.hans.domain.wordgame.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.wordgame.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameServiceImpl implements WordGameService{

    private final WordRepository wordRepository;
    private final WordGameRoomService wordGameRoomService;
    @Override
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence) {
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);

        Word word = wordRepository.findByWordSequence(wordGameRoom.getWordsSequence().get(wordGameAnswerRequestDto.getQuestionmNum()));

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
