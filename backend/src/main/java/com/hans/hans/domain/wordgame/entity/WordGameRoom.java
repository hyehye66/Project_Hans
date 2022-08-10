package com.hans.hans.domain.wordgame.entity;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.wordgame.repository.WordRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class WordGameRoom {

    private long roomSequence;//방번호
    private int totalQuestion;//문제수
    private List<Long> wordsSequence;//문제 번호
    private Map<String, Integer> players;
    private Map<String, Integer> correctPlayers;//문제 맞춘 사람

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final WordRepository wordRepository;

    public void createWordGame(long roomSequence, int totalQuestion){
        this.roomSequence = roomSequence;
        this.totalQuestion = totalQuestion;
    }
    public void initWordGame(){
        //사람 갱신
        Room room = roomRepository.findByRoomSequence(this.roomSequence);
        List<RoomMember> roomMembers = roomMemberRepository.findRoomMembersByRoom(room);
        for(RoomMember roomMember :roomMembers ){
            Member member = roomMember.getMember();
            players.put(member.getNickname(),0);
        }

        //문제 갱신
        List<Word> words = wordRepository.findAll();
        wordsSequence = new ArrayList<>();

        for(Word word : words){
            wordsSequence.add(word.getWordSequence());
        }

        Collections.shuffle(wordsSequence);
        wordsSequence.subList(0, totalQuestion);
    }

}
