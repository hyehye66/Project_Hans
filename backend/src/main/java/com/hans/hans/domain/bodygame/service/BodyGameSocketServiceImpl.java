package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.*;
import com.hans.hans.domain.bodygame.entity.BodyGameRoom;
import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.word.repository.WordRepository;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitResponseDto;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.global.enumerate.Modes;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

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

        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);
        List<String> players = new ArrayList<>(bodyGameRoom.getPlayers().keySet());

        BodyGameStartResponseDto bodyGameStartResponseDto = new BodyGameStartResponseDto(players,"ready");

        roomService.updateRoomStatus(roomSequence,true);

        return bodyGameStartResponseDto;
    }

    @Override
    public BodyGameProblemResponseDto getProblem(Long roomSequence, int problemNum){
        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);

        Long wordSequence = bodyGameRoom.getWordsSequence().get(problemNum-1);

        Word word = wordRepository.findByWordSequence(wordSequence);
        BodyGameProblemResponseDto bodyGameProblemResponseDto = new BodyGameProblemResponseDto(word);

        return bodyGameProblemResponseDto;
    }

    @Override
    public BodyGameSubmitResponseDto submit (BodyGameSubmitRequestDto bodyGameSubmitRequestDto, Long roomSequence){

        String player = bodyGameSubmitRequestDto.getPlayer();
        String submit = bodyGameSubmitRequestDto.getSubmit();
        int problemNum = bodyGameSubmitRequestDto.getProblemNum()-1;
        int time = bodyGameSubmitRequestDto.getTime();

        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);
        Long wordSequence = bodyGameRoom.getWordsSequence().get(problemNum);

        String answer = wordRepository.findByWordSequence(wordSequence).getWord();
        Long difficulty = wordRepository.findByWordSequence(wordSequence).getDifficulty();

        if(answer.equals(submit)) {
            Long score = time + difficulty;
            bodyGameRoom.updateCorrectPlayers(player, score);
            bodyGameRoom.updatePlayersScore(player, score);
        }

        JSONObject jsonObject = new JSONObject(bodyGameRoom.getCorrectPlayers());
        BodyGameSubmitResponseDto bodyGameSubmitResponseDto = new BodyGameSubmitResponseDto(jsonObject);

        return bodyGameSubmitResponseDto;
    }

    @Override
    public BodyGameAnswerResponseDto getAnswer(BodyGameAnswerRequestDto bodyGameAnswerRequestDto, Long roomSequence) {
        BodyGameRoom bodyGameRoom = bodyGameRoomService.findById(roomSequence);

        int problemNum = bodyGameAnswerRequestDto.getQuestionNum()-1;

        Word word = wordRepository.findByWordSequence(bodyGameRoom.getWordsSequence().get(problemNum));

        JSONObject jsonObject = new JSONObject(bodyGameRoom.getCorrectPlayers());

        BodyGameAnswerResponseDto bodyGameAnswerResponseDto =
                BodyGameAnswerResponseDto.builder()
                        .correctPlayers(jsonObject)
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

        JSONObject jsonObject = new JSONObject(result);
        BodyGameResultResponseDto bodyGameResultResponseDto = new BodyGameResultResponseDto(jsonObject);

        return bodyGameResultResponseDto;
    }

}
