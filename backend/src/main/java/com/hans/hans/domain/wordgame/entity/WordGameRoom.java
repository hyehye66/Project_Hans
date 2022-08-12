package com.hans.hans.domain.wordgame.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Getter
public class WordGameRoom {

    private Long roomSequence;//방번호
    private int totalQuestion;//문제수
    private List<Long> wordsSequence;//문제 번호 1,3,8,42,13
    private Map<String, Long> players;
    private Set<String> correctPlayers;//문제 맞춘 사람

    public void initPlayers(List<String>playerNames){
        //사람 갱신
        players = new HashMap<>();
        for(String playerName : playerNames ){
          players.put(playerName,Long.valueOf(0));
        }
        //맞은 사람 갱신
        correctPlayers = new HashSet<>();

    }
    public void initWordsSequence(List<Long> wordsSequence){
        this.wordsSequence = wordsSequence;
    }
    public static WordGameRoom createWordGame(Long roomSequence, int totalQuestion){
        WordGameRoom  room  = new WordGameRoom();
        room.roomSequence = roomSequence;
        room.totalQuestion = totalQuestion;
        return room;
    }

    public void resetCorrectPlayers(){
        this.correctPlayers = new HashSet<>();
    }

    //문제 맞힌 사람 추가
    public void updateCorrectPlayers(Set<String> correctPlayers){
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
