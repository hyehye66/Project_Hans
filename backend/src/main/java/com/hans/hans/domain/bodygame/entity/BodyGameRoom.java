package com.hans.hans.domain.bodygame.entity;

import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Getter
public class BodyGameRoom {

    private Long roomSequence;//방번호
    private int totalQuestion;//문제수
    private Long difficulty;
    private List<Long> wordsSequence;//문제 번호
    String gameStatus;//게임 상태(게임 대기="ready", 게임 시작중="start", 정답 알려주는중="pause")//enum
    private Map<String, Long> players;
    private Map<String, Long> correctPlayers;//문제 맞춘 사람

    public void initPlayers(List<String>playerNames){
        //사람 갱신
        players = new HashMap<>();
        for(String playerName : playerNames ){
            players.put(playerName,0L);
        }
        //맞은 사람 갱신
        correctPlayers = new HashMap<>();
    }

    public void initWordsSequence(List<Long> wordsSequence){
        this.wordsSequence = wordsSequence;
    }

    public static BodyGameRoom createBodyGame(Long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto){
        BodyGameRoom room  = new BodyGameRoom();
        room.roomSequence = roomSequence;
        room.totalQuestion = bodyGameStartRequestDto.getTotalQuestion();
        room.difficulty = bodyGameStartRequestDto.getDifficulty();
        return room;
    }

    public void refreshCorrectPlayers(){
        this.correctPlayers = new HashMap<>();
    }
    public void updatePlayersScore(String player, Long score){
        this.players.put(player,players.get(player)+score);
    }

    public void updateCorrectPlayers(String player, Long score){
        this.correctPlayers.put(player,players.get(player)+score);
    }
}
