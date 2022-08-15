package com.hans.hans.domain.bodygame.controller;

import com.hans.hans.domain.bodygame.dto.*;
import com.hans.hans.domain.bodygame.service.BodyGameSocketService;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameSubmitResponseDto;
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
    public BodyGameStartResponseDto initGame(@DestinationVariable("room_seq") Long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto) {
        return bodyGameSocketService.initGame(roomSequence, bodyGameStartRequestDto);
    }

    @MessageMapping("/body-game/room/{room_seq}/problem/{problem_num}")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameProblemResponseDto getProblem(@DestinationVariable("room_seq") Long roomSequence, @DestinationVariable("problem_num") int problemNum){
        return bodyGameSocketService.getProblem(roomSequence, problemNum);
    }

    @MessageMapping("/body-game/submit/{room_seq}")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameSubmitResponseDto submit(BodyGameSubmitRequestDto bodyGameSubmitRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        BodyGameSubmitResponseDto bodyGameSubmitResponseDto = bodyGameSocketService.submit(bodyGameSubmitRequestDto,roomSequence);
        return bodyGameSubmitResponseDto;
    }

    @MessageMapping("/body-game/answer/{room_seq}")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameAnswerResponseDto getAnswer(BodyGameAnswerRequestDto bodyGameAnswerRequestDto, @DestinationVariable("room_seq") Long roomSequence){
        BodyGameAnswerResponseDto bodyGameAnswerResponseDto = bodyGameSocketService.getAnswer(bodyGameAnswerRequestDto, roomSequence);
        return bodyGameAnswerResponseDto;
    }

    @MessageMapping("/body-game/result/{room_seq}")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameResultResponseDto getResult(@DestinationVariable("room_seq") Long roomSequence){
        BodyGameResultResponseDto bodyGameResultResponseDto = bodyGameSocketService.getResult(roomSequence);
        return bodyGameResultResponseDto;
    }
    @MessageMapping("/body-game/room/{room_seq}/owner")
    @SendTo("/topic/body-game/{room_seq}")
    public BodyGameOwnerResponseDto getOwner(@DestinationVariable("room_seq") Long roomSequence){
        BodyGameOwnerResponseDto bodyGameOwnerResponseDto = bodyGameSocketService.getOwner(roomSequence);
        return bodyGameOwnerResponseDto;
    }

}
