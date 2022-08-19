package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WordGameRoomService {

    private Map<Long, WordGameRoom> wordGameRooms;
    private WordGameRoom wordGameRoom;

    private final RoomRepository roomRepository;
    private final RoomMemberRepository roomMemberRepository;
    private final WordRepository wordRepository;
    @PostConstruct
    private void init() {
        wordGameRooms = new LinkedHashMap<>();
    }

    public List<WordGameRoom> findAllWordGameRooms() {
        List<WordGameRoom> result = new ArrayList<>(wordGameRooms.values());
        Collections.reverse(result);

        return result;
    }
    public WordGameRoom findById(Long roomId) {
        return wordGameRooms.get(roomId);
    }

    public void createWordGameRoom(Long roomSequence, int totalQuestion) {
        //방번호,문제번호 지정.
        wordGameRoom = WordGameRoom.createWordGame(roomSequence,totalQuestion);
        //플레이어 넣기.
        Room room = roomRepository.findByRoomSequence(roomSequence);

        List<RoomMember> roomMembers = roomMemberRepository.findRoomMembersByRoom(room);
        List<String> playerNames = new ArrayList<>();
        for(RoomMember roomMember :roomMembers ){
            Member member = roomMember.getMember();
            playerNames.add(member.getNickname());
        }
        wordGameRoom.initPlayers(playerNames);

        //문제 갱신
        List<Word> words = wordRepository.findAll();
        List<Long> wordsSequence = new ArrayList<>();

        for(Word word : words){
            wordsSequence.add(word.getWordSequence());
        }
        Collections.shuffle(wordsSequence);
        wordsSequence.subList(0, totalQuestion);
        wordGameRoom.initWordsSequence(wordsSequence);
        //map 넣기
        wordGameRooms.put(roomSequence, wordGameRoom);

        //체크 하려고 잠시 넣어둠 나중에 검증 끝나고 빼도 됨.
        System.out.print("wordSequenceInit (");
        for(Long wordSequence: wordsSequence){
            System.out.print(wordSequence+",");
        }
        System.out.println();

    }

}
