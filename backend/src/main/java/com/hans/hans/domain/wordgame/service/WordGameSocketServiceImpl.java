package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.ranking.service.RankingService;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.word.repository.WordRepository;
import com.hans.hans.global.enumerate.Modes;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameSocketServiceImpl implements WordGameSocketService {
    private final RoomRepository roomRepository;
    private final WordRepository wordRepository;
    private final WordGameRoomService wordGameRoomService;
    private final RankingService rankingService;
    private final RoomService roomService;

    @Override
    public WordGameStartResponseDto initGame(Long roomSequence, WordGameStartRequestDto wordGameStartRequestDto){
        wordGameRoomService.createWordGameRoom(roomSequence, wordGameStartRequestDto.getTotalQuestion());

        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        List<String> players = new ArrayList<>(wordGameRoom.getPlayers().keySet());

        roomService.updateRoomStatus(roomSequence,true);

        WordGameStartResponseDto wordGameStartResponseDto =
                WordGameStartResponseDto.builder()
                        .players(players)
                        .gameStatus("ready")
                        .totalQuestion(wordGameStartRequestDto.getTotalQuestion())
                        .build();

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
        int problemNum = wordGameSubmitRequestDto.getProblemNum()-1;

        WordGameRoom wordGameRoom = wordGameRoomService.findById(roomSequence);
        Long wordSequence = wordGameRoom.getWordsSequence().get(problemNum);

        String answer = wordRepository.findByWordSequence(wordSequence).getWord();
        Long difficulty = wordRepository.findByWordSequence(wordSequence).getDifficulty();

        if(answer.equals(submit)) {
            wordGameRoom.updateCorrectPlayers(player);
            wordGameRoom.updatePlayersScore(player, difficulty);
        }

        WordGameSubmitResponseDto wordGameSubmitResponseDto = new WordGameSubmitResponseDto(wordGameRoom.getCorrectPlayers());

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

        List<String> playersKeySet = new ArrayList<>(players.keySet());

        playersKeySet.sort((o1, o2) -> players.get(o2).compareTo(players.get(o1)));

        Map<String,Long> result = new HashMap<>();

        for(String key: playersKeySet){
            result.put(key,players.get(key));
            rankingService.updateRanking(key, Modes.WORD, players.get(key));
        }

        roomService.updateRoomStatus(roomSequence,false);

        JSONObject jsonObject = new JSONObject(result);
        WordGameResultResponseDto wordGameResultResponseDto = new WordGameResultResponseDto(jsonObject);

        return wordGameResultResponseDto;
    }
    @Override
    public WordGameOwnerResponseDto getOwner(WordGameOwnerRequestDto wordGameOwnerRequestDto, Long roomSequence){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        String owner = room.getMember().getNickname();
        WordGameOwnerResponseDto wordGameOwnerResponseDto = new WordGameOwnerResponseDto(owner, wordGameOwnerRequestDto.getTotalQuestion());
        return wordGameOwnerResponseDto;
    }
}
