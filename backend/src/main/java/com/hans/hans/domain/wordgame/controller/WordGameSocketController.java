package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.wordgame.service.WordGameSocketService;

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

    @MessageMapping("/word-game/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameStartResponseDto initGame(@DestinationVariable("room_seq") long roomSequence, WordGameStartRequestDto wordGameStartRequestDto) {
        return wordGameSocketService.initGame(roomSequence, wordGameStartRequestDto);
    }

    @MessageMapping("/game/answer/{room_seq}")
    @SendTo("/topic/game/answer/{room_seq}")
    public void getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameAnswerResponseDto wordGameAnswerResponseDto = wordGameSocketService.getAnswer(wordGameAnswerRequestDto, roomSequence);
        sendingOperations.convertAndSend(wordGameAnswerResponseDto);
    }

    @MessageMapping("game/result/{room_seq}")
    @SendTo("/topic/game/result/{room_seq}")
    public void getResult(@DestinationVariable("room_seq") Long roomSequence){
        WordGameResultResponseDto wordGameResultResponseDto = wordGameSocketService.getResult(roomSequence);
        sendingOperations.convertAndSend(wordGameResultResponseDto);
    }

}
