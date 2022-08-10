package com.hans.hans.domain.wordgame.service;

import com.hans.hans.domain.wordgame.entity.WordGameRoom;
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
    private final WordGameRoom wordGameRoom;

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

    public WordGameRoom createWordGameRoom(Long roomSequence) {
        wordGameRoom.initWordGame();
        wordGameRooms.put(roomSequence, wordGameRoom);
        return wordGameRoom;
    }
}
