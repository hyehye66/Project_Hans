package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.word.entity.Word;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitResponseDto;
import com.hans.hans.domain.wordgame.entity.WordGameRoom;
import com.hans.hans.domain.word.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
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
        wordGameRoom.initPlayers(playerNames);//

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
    public long findWordSequence(Long roomSequence , int problemNum){
        wordGameRoom = wordGameRooms.get(roomSequence);
        //room 에 problemNum 을 넘겨서 wordSequence 받기
        long wordNum = wordGameRoom.getWordNum(problemNum);
        return wordNum;
    }
    public WordGameSubmitResponseDto submit (WordGameSubmitRequestDto wordGameSubmitRequestDto, Long roomSequence){
        //request 들
        String player = wordGameSubmitRequestDto.getPlayer();
        String submit = wordGameSubmitRequestDto.getSubmit();
        int problemNum = wordGameSubmitRequestDto.getProblemNum();

        //room 의 정보들 가져오기
        wordGameRoom = wordGameRooms.get(roomSequence);
        Long wordSequence = wordGameRoom.getWordsSequence().get(problemNum);
        Map<String, Long>players = wordGameRoom.getPlayers();
        Map<String,Long>correctPlayers = wordGameRoom.getCorrectPlayers();

        //DB에서 정보 가져 오기
        String answer = wordRepository.findByWordSequence(wordSequence).getWord();
        Long difficulty = wordRepository.findByWordSequence(wordSequence).getDifficulty();
        //정답자가 아니고 맞았다면 점수 갱신 하고 return
        //정답자 아니라면
        if(!correctPlayers.containsKey(correctPlayers)) {
            //맞았다면
            if (answer.equals(submit)) {
                correctPlayers.put(player, difficulty);
                Long prevPoint = players.get(player);
                players.put(player, prevPoint + difficulty);
            }
        }
        List<String>correctPlayerList = new ArrayList<>();
        List<Long>points = new ArrayList<>();
        for( String key : correctPlayers.keySet() ){
            correctPlayerList.add(key);
            points.add(players.get(key));
        }
        return WordGameSubmitResponseDto.builder()
                .players(correctPlayerList)
                .points(points)
                .build();
    }
}
