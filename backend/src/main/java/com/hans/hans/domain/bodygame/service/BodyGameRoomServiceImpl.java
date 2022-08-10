package com.hans.hans.domain.bodygame.service;


import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.entity.BodyGameRoom;
import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BodyGameRoomServiceImpl implements BodyGameRoomService {

    private Map<Long, BodyGameRoom> bodyGameRooms;
    private final BodyGameRoom bodyGameRoom;

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final WordRepository wordRepository;

    public void createBodyGameRoom(Long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto){
        //방번호, 문제, 난이도 설정
        bodyGameRoom.createBodyGame(roomSequence,bodyGameStartRequestDto);
        //플레이어 넣기
        Room room = roomRepository.findByRoomSequence(roomSequence);

        List<RoomMember> roomMembers = roomMemberRepository.findRoomMembersByRoom(room);
        List<String> playerNames = new ArrayList<>();
        for(RoomMember roomMember :roomMembers ){
            Member member = roomMember.getMember();
            playerNames.add(member.getNickname());
        }
        bodyGameRoom.initPlayers(playerNames);
        //문제 갱신
        //난이도로 뽑아서 셔플
        List<Word>words = wordRepository.findWordsByDifficulty(bodyGameRoom.getDifficulty());
        List<Long> wordsSequence = new ArrayList<>();
        for(Word word : words){
            wordsSequence.add(word.getWordSequence());
        }
        Collections.shuffle(wordsSequence);
        wordsSequence.subList(0, bodyGameRoom.getTotalQuestion());
        bodyGameRoom.initWordsSequence(wordsSequence);
        //map 넣기
        bodyGameRooms.put(roomSequence, bodyGameRoom);

    }
}
