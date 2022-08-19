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
    private List<String> correctPlayers;//문제 맞춘 사람

    public void initPlayers(List<String>playerNames){
        //사람 갱신
        players = new HashMap<>();
        for(String playerName : playerNames ){
          players.put(playerName,0L);
        }
        //맞은 사람 갱신
        correctPlayers = new ArrayList<>();

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
        this.correctPlayers = new ArrayList<>();
    }

    //문제 맞힌 사람 추가
    public void updateCorrectPlayers(String player){
        this.correctPlayers.add(player);
    }

    public void updatePlayersScore(String player, Long score){
        this.players.put(player,players.get(player)+score);
    }

}
