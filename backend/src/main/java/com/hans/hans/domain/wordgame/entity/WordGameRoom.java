package com.hans.hans.domain.wordgame.entity;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.wordgame.dto.WordGameStartResponseDto;
import com.hans.hans.domain.wordgame.repository.WordGameRepository;
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
    private List<Long> wordsSequence;//문제 번호

    String gameStatus;//게임 상태(게임 대기="ready", 게임 시작중="start", 정답 알려주는중="pause")//enum
    private Map<String, Long> players;
    private Map<String, Long> correctPlayers;//문제 맞춘 사람

    public void initPlayers(List<String>playerNames){
        //사람 갱신
        //this.players = players;
        for(String playerName : playerNames ){
          players.put(playerName,0l);
        }
        //맞은 사람 갱신
        correctPlayers = new HashMap<>();
    }
    public void initWordsSequence(List<Long> wordsSequence){
        this.wordsSequence = wordsSequence;
    }
    public static void createWordGame(long roomSequence, int totalQuestion){
        WordGameRoom  room  = new WordGameRoom();
        room.roomSequence = roomSequence;
        room.totalQuestion = totalQuestion;
    }

    public void refreshCorrectPlayers(){
        this.correctPlayers = new HashMap<>();
    }

    public void createWordGame(long roomSequence, int totalQuestion){
        this.roomSequence = roomSequence;
        this.totalQuestion = totalQuestion;
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
