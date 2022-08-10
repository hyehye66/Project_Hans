package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.wordgame.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.wordgame.repository.WordRepository;
import com.hans.hans.global.enumerate.Modes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameSocketServiceImpl implements WordGameSocketService {
    private final WordRepository wordRepository;
    private final WordGameRoomService wordGameRoomService;
    private final RankingService rankingService;
    private final RoomService roomService;

    @Override
    public WordGameStartResponseDto initGame(long roomSequence, WordGameStartRequestDto wordGameStartRequestDto){
        wordGameRoomService.createWordGameRoom(roomSequence, wordGameStartRequestDto.getTotalQuestion());
        WordGameStartResponseDto wordGameStartResponseDto = new WordGameStartResponseDto("ready");

        roomService.updateRoomStatus(roomSequence,true);

        return wordGameStartResponseDto;
    }

    @Override
    public WordGameProblemResponseDto getProblem(Long wordSequence){
        Word word = wordRepository.findByWordSequence(wordSequence);
        WordGameProblemResponseDto wordGameProblemResponseDto = new WordGameProblemResponseDto(word);
        return wordGameProblemResponseDto;
    }

    @Override
    public WordGameSubmitResponseDto checkAnswer(WordGameSubmitRequestDto wordGameSubmitRequestDto, Long wordSequence, Long roomSequence){
        Word word = wordRepository.findByWordSequence(wordSequence);
        String answer = word.getWord();
        String player = wordGameSubmitRequestDto.getPlayer();
        WordGameSubmitResponseDto wordGameSubmitResponseDto;
        if (wordGameSubmitRequestDto.getSubmit().equals(answer)){
            WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
            //player 점수 갱신
            Map<String, Long> players = wordGameRoom.getPlayers();
            wordGameRoom.addPlayerPoint(players, player, word.getDifficulty());

            // collectPlayers 추가
            Map<String, Long> correctPlayers = new HashMap<>();
            correctPlayers.put(player,word.getDifficulty());
            wordGameRoom.addCorrectPlayers(correctPlayers);

            //responseDto
            wordGameSubmitResponseDto = WordGameSubmitResponseDto.builder()
                    .submit(wordGameSubmitRequestDto.getSubmit())
                    .isAnswer(true)
                    .build();
        } else {
            wordGameSubmitResponseDto = WordGameSubmitResponseDto.builder()
                    .submit(wordGameSubmitRequestDto.getSubmit())
                    .isAnswer(false)
                    .build();
        }
        return wordGameSubmitResponseDto;
    }


    @Override
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence) {
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);

        Word word = wordRepository.findByWordSequence(wordGameRoom.getWordsSequence().get(wordGameAnswerRequestDto.getQuestionNum()));

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

        roomService.updateRoomStatus(roomSequence,false);

        WordGameResultResponseDto wordGameResultResponseDto = new WordGameResultResponseDto(result);

        return wordGameResultResponseDto;
    }
}
