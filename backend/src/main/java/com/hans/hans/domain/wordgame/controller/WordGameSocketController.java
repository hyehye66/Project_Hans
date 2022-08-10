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

    @MessageMapping("/word-game/problem/{room_seq}")
    @SendTo("/topic/word-game/problem/{room_seq}")
    public WordGameProblemResponseDto getProblem(@DestinationVariable("room_seq") long roomSequence, Long wordSequence) {
        return wordGameSocketService.getProblem(wordSequence);
    }

    @MessageMapping("/word-game/answer/{room_seq}")
    @SendTo("/topic/word-game/answer/{room_seq}")
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameAnswerResponseDto wordGameAnswerResponseDto = wordGameSocketService.getAnswer(wordGameAnswerRequestDto, roomSequence);
        return wordGameAnswerResponseDto;
    }

    @MessageMapping("word-game/result/{room_seq}")
    @SendTo("/topic/word-game/result/{room_seq}")
    public WordGameResultResponseDto getResult(@DestinationVariable("room_seq") Long roomSequence){
        WordGameResultResponseDto wordGameResultResponseDto = wordGameSocketService.getResult(roomSequence);
        return wordGameResultResponseDto;
    }

}
