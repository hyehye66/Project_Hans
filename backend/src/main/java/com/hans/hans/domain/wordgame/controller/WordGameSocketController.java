package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.service.WordGameSocketService;
import com.hans.hans.domain.wordgame.dto.WordGameStartRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameStartResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WordGameSocketController {
    private final SimpMessageSendingOperations sendingOperations;
    private final WordGameSocketService wordGameSocketService;

    @MessageMapping("/game/answer/{room_seq}")
    @SendTo("/topic/game/answer/{room_seq}")
    public void getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameAnswerResponseDto wordGameAnswerResponseDto = wordGameSocketService.getAnswer(wordGameAnswerRequestDto, roomSequence);
        //sendingOperations.convertAndSend(wordGameAnswerResponseDto);
    }

    @MessageMapping("/word-game/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameStartResponseDto initGame(@DestinationVariable("room_seq") long roomSequence, WordGameStartRequestDto wordGameStartRequestDto) {
        return wordGameSocketService.initGame(roomSequence, wordGameStartRequestDto);
    }

}
