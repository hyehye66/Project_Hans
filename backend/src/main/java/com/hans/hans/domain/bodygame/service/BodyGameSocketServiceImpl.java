package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.*;
import com.hans.hans.domain.bodygame.entity.BodyGameRoom;
import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.word.repository.WordRepository;
import com.hans.hans.global.enumerate.Modes;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BodyGameSocketServiceImpl implements BodyGameSocketService {

    private final BodyGameRoomService bodyGameRoomService;
    private final WordRepository wordRepository;
    private final RankingService rankingService;
    private final RoomService roomService;


    @Override
    public BodyGameStartResponseDto initGame(Long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto){
        bodyGameRoomService.createBodyGameRoom(roomSequence,bodyGameStartRequestDto);
        BodyGameStartResponseDto bodyGameStartResponseDto = new BodyGameStartResponseDto("ready");
        return bodyGameStartResponseDto;
    }

    @Override
    public BodyGameProblemResponseDto getProblem(Long wordSequence){
        Word word = wordRepository.findByWordSequence(wordSequence);
        BodyGameProblemResponseDto bodyGameProblemResponseDto = new BodyGameProblemResponseDto(word);
        return bodyGameProblemResponseDto;
    }

    @Override
    public BodyGameAnswerResponseDto getAnswer(BodyGameAnswerRequestDto bodyGameAnswerRequestDto, Long roomSequence) {
        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);

        Word word = wordRepository.findByWordSequence(bodyGameRoom.getWordsSequence().get(bodyGameAnswerRequestDto.getQuestionNum()));

        JSONObject json = new JSONObject(bodyGameRoom.getCorrectPlayers());

        BodyGameAnswerResponseDto bodyGameAnswerResponseDto =
                BodyGameAnswerResponseDto.builder()
                        .correctPlayers(json)
                        .roomSequence(bodyGameRoom.getRoomSequence())
                        .answer(word.getWord())
                        .point(word.getDifficulty())
                        .build();

        bodyGameRoom.refreshCorrectPlayers();

        return bodyGameAnswerResponseDto;
    }

    @Override
    public BodyGameResultResponseDto getResult(Long roomSequence){
        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);
        Map<String,Long> players = bodyGameRoom.getPlayers();

        List<String> playersKeySet = new ArrayList<>(players.keySet());

        playersKeySet.sort((o1, o2) -> players.get(o2).compareTo(players.get(o1)));

        Map<String,Long> result = new HashMap<>();

        for(String key: playersKeySet){
            result.put(key,players.get(key));
            rankingService.updateRanking(key, Modes.BODY, players.get(key));
        }

        roomService.updateRoomStatus(roomSequence,false);

        JSONObject json = new JSONObject(result);

        BodyGameResultResponseDto bodyGameResultResponseDto = new BodyGameResultResponseDto(json);

        return bodyGameResultResponseDto;
    }

}
