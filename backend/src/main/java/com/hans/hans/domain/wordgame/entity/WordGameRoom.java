package com.hans.hans.domain.wordgame.entity;


import com.hans.hans.domain.wordgame.dto.WordGameSubmitRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor

@Getter
public class WordGameRoom {

    private long roomSequence;//방번호
    private int totalQuestion;//문제수
    private List<Long> wordsSequence;//문제 번호 1,3,8,42,13

    String gameStatus;//게임 상태(게임 대기="ready", 게임 시작중="start", 정답 알려주는중="pause")//enum
    private Map<String, Long> players;
    private Map<String, Long> correctPlayers;//문제 맞춘 사람

    public void initPlayers(List<String>playerNames){
        //사람 갱신
        players = new HashMap<>();
        for(String playerName : playerNames ){
          players.put(playerName,Long.valueOf(0));
        }
        //맞은 사람 갱신
        correctPlayers = new HashMap<>();

    }
    public void initWordsSequence(List<Long> wordsSequence){
        this.wordsSequence = wordsSequence;
    }
    public static WordGameRoom createWordGame(long roomSequence, int totalQuestion){
        WordGameRoom  room  = new WordGameRoom();
        room.roomSequence = roomSequence;
        room.totalQuestion = totalQuestion;
        return room;
    }

    public long getWordNum(int problemNum){
        return wordsSequence.get(problemNum-1);
    }

    public void refreshCorrectPlayers(){
        this.correctPlayers = new HashMap<>();
    }

    //문제 맞힌 사람 추가
    public void addCorrectPlayers(Map<String, Long> correctPlayers){
        this.correctPlayers = correctPlayers;
    }

    //맞힌 점수 갱신
    public void addPlayerPoint(Map<String, Long> players, String player, Long point){
        for (String key : players.keySet()) {
            if (key.equals(player)){
                players.put(key, players.get(key) + point);
            }
        }
    }
}
