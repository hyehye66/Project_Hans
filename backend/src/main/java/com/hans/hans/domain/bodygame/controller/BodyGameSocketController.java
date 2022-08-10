package com.hans.hans.domain.bodygame.controller;

import com.hans.hans.domain.bodygame.dto.BodyGameProblemResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartResponseDto;
import com.hans.hans.domain.bodygame.service.BodyGameSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BodyGameSocketController {

    private final BodyGameSocketService bodyGameSocketService;

    @MessageMapping("/body-game/{room_seq}")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameStartResponseDto initGame(@DestinationVariable("room_seq") long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto) {
        return bodyGameSocketService.initGame(roomSequence, bodyGameStartRequestDto);
    }

    @MessageMapping("/body-game/problem/{room_seq}")
    @SendTo("/topic/body-game/problem/{room_seq}")
    public BodyGameProblemResponseDto getProblem(Long wordSequence, @DestinationVariable("room_seq") Long roomSequence){
        return bodyGameSocketService.getProblem(wordSequence);
    }




}
