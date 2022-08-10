package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.wordgame.dto.WordGameAnswerRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameAnswerResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameResultResponseDto;
import com.hans.hans.domain.wordgame.service.WordGameService;
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
    private final WordGameService wordGameService;

    @MessageMapping("/game/answer/{room_seq}")
    @SendTo("/topic/game/answer/{room_seq}")
    public void getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameAnswerResponseDto wordGameAnswerResponseDto = wordGameService.getAnswer(wordGameAnswerRequestDto, roomSequence);
        sendingOperations.convertAndSend(wordGameAnswerResponseDto);
    }

    @MessageMapping("game/result/{room_seq}")
    @SendTo("/topic/game/result/{room_seq}")
    public void getResult(@DestinationVariable("room_seq") Long roomSequence){
        WordGameResultResponseDto wordGameResultResponseDto = wordGameService.getResult(roomSequence);
        sendingOperations.convertAndSend(wordGameResultResponseDto);
    }

}
