package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.word.repository.WordRepository;
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

        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        List<String> players = new ArrayList<>(wordGameRoom.getPlayers().keySet());

        WordGameStartResponseDto wordGameStartResponseDto = new WordGameStartResponseDto(players,"ready");

        roomService.updateRoomStatus(roomSequence,true);

        return wordGameStartResponseDto;
    }

    @Override
    public WordGameProblemResponseDto getProblem(Long roomSequence , int problemNum){
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);

        Long wordSequence = wordGameRoom.getWordsSequence().get(problemNum-1);

        Word word = wordRepository.findByWordSequence(wordSequence);
        WordGameProblemResponseDto wordGameProblemResponseDto = new WordGameProblemResponseDto(word);

        return wordGameProblemResponseDto;

    }

    @Override
    public WordGameSubmitResponseDto submit (WordGameSubmitRequestDto wordGameSubmitRequestDto, Long roomSequence){
        String player = wordGameSubmitRequestDto.getPlayer();
        String submit = wordGameSubmitRequestDto.getSubmit();
        int problemNum = wordGameSubmitRequestDto.getProblemNum();

        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        Long wordSequence = wordGameRoom.getWordsSequence().get(problemNum);
        Map<String, Long> players = wordGameRoom.getPlayers();

        Set<String> correctPlayers = wordGameRoom.getCorrectPlayers();
        int cntCorrectPlayers = correctPlayers.size();

        String answer = wordRepository.findByWordSequence(wordSequence).getWord();
        Long difficulty = wordRepository.findByWordSequence(wordSequence).getDifficulty();

        if(answer.equals(submit)){
            correctPlayers.add(player);
            if(cntCorrectPlayers!=correctPlayers.size()){
                Long prevPoint = players.get(player);
                players.put(player, prevPoint + difficulty);
            }
        }

        wordGameRoom.updateCorrectPlayers(correctPlayers);
        WordGameSubmitResponseDto wordGameSubmitResponseDto = new WordGameSubmitResponseDto(correctPlayers);

        return wordGameSubmitResponseDto;
    }


    @Override
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, Long roomSequence) {
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        int problemIndex =  wordGameAnswerRequestDto.getQuestionNum()-1;
        Word word = wordRepository.findByWordSequence(wordGameRoom.getWordsSequence().get(problemIndex));

        WordGameAnswerResponseDto wordGameAnswerResponseDto =
                WordGameAnswerResponseDto.builder()
                        .correctPlayers(wordGameRoom.getCorrectPlayers())
                        .roomSequence(wordGameRoom.getRoomSequence())
                        .answer(word.getWord())
                        .point(word.getDifficulty())
                        .build();

        wordGameRoom.resetCorrectPlayers();

        return wordGameAnswerResponseDto;
    }

    @Override
    public WordGameResultResponseDto getResult(Long roomSequence){
        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        Map<String,Long> players = wordGameRoom.getPlayers();
        List<String>playerList = new ArrayList<>();
        List<Long>points = new ArrayList<>();

        List<String> playersKeySet = new ArrayList<>(players.keySet());

        playersKeySet.sort((o1, o2) -> players.get(o2).compareTo(players.get(o1)));

        Map<String,Long> result = new HashMap<>();

        for(String key: playersKeySet){
            result.put(key,players.get(key));
            rankingService.updateRanking(key, Modes.WORD, players.get(key));
            playerList.add(key);
            points.add(players.get(key));
        }

        roomService.updateRoomStatus(roomSequence,false);

        WordGameResultResponseDto wordGameResultResponseDto = new WordGameResultResponseDto(playerList,points);

        return wordGameResultResponseDto;
    }
}
