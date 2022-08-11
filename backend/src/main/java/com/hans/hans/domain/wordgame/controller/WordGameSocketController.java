package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.wordgame.dto.*;
import com.hans.hans.domain.wordgame.service.WordGameSocketService;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WordGameSocketController {
    private final WordGameSocketService wordGameSocketService;

    @MessageMapping("/word-game/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameStartResponseDto initGame(@DestinationVariable("room_seq") long roomSequence, WordGameStartRequestDto wordGameStartRequestDto) {
        System.out.println("서버쪽 받았음!");
        return wordGameSocketService.initGame(roomSequence, wordGameStartRequestDto);
    }

    @MessageMapping("/word-game/room/{room_seq}/problem/{problem_num}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameProblemResponseDto getProblem(@DestinationVariable("room_seq") Long roomSequence, @DestinationVariable("problem_num") int problemNum) {
        return wordGameSocketService.getProblem(roomSequence, problemNum);
    }

    @MessageMapping("/word-game/check/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameSubmitResponseDto submit(WordGameSubmitRequestDto wordGameSubmitRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameSubmitResponseDto wordGameSubmitResponseDto = wordGameSocketService.submit(wordGameSubmitRequestDto,roomSequence);
        return wordGameSubmitResponseDto;
    }

    @MessageMapping("/word-game/answer/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameAnswerResponseDto getAnswer(WordGameAnswerRequestDto wordGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        WordGameAnswerResponseDto wordGameAnswerResponseDto = wordGameSocketService.getAnswer(wordGameAnswerRequestDto, roomSequence);
        return wordGameAnswerResponseDto;
    }

    @MessageMapping("/word-game/result/{room_seq}")
    @SendTo("/topic/word-game/{room_seq}")
    public WordGameResultResponseDto getResult(@DestinationVariable("room_seq") Long roomSequence){
        WordGameResultResponseDto wordGameResultResponseDto = wordGameSocketService.getResult(roomSequence);
        return wordGameResultResponseDto;
    }

}
