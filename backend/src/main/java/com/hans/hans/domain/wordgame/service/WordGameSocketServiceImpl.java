package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.wordgame.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.wordgame.repository.WordGameRepository;
import com.hans.hans.domain.wordgame.repository.WordRepository;
import com.hans.hans.global.enumerate.Modes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameSocketServiceImpl implements WordGameSocketService {
    private final WordGameRepository wordGameRepository;
    private final WordRepository wordRepository;
    private final WordGameRoomService wordGameRoomService;
    private final RankingService rankingService;

    @Override
    public WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto){
        wordGameRoomService.createWordGameRoom(roomSequence, wordGameStartRequestDto.getTotalQuestion());
        WordGameStartResponseDto wordGameStartResponseDto = new WordGameStartResponseDto("ready");

        return wordGameStartResponseDto;
    }

    @Override
    public WordGameProblemResponseDto getProblem(Long wordSequence){
        Word word = wordRepository.findByWordSequence(wordSequence);
        WordGameProblemResponseDto wordGameProblemResponseDto = new WordGameProblemResponseDto(word);
        return wordGameProblemResponseDto;
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

        wordGameRoom.refreshCorrectPlayers();

        return wordGameAnswerResponseDto;
    }

    @Override
    public WordGameResultResponseDto getResult(Long roomSequence){
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        Map<String,Long> players = wordGameRoom.getPlayers();

        List<String> playersKeySet = new ArrayList<>(players.keySet());

        playersKeySet.sort((o1, o2) -> players.get(o2).compareTo(players.get(o1)));

        Map<String,Long> result = new HashMap<>();

        for(String key: playersKeySet){
            result.put(key,players.get(key));
            rankingService.updateRanking(key, Modes.WORD, players.get(key));
        }

        WordGameResultResponseDto wordGameResultResponseDto = new WordGameResultResponseDto(result);

        return wordGameResultResponseDto;
    }
}
