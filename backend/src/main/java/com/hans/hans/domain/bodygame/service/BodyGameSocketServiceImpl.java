package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameStartResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BodyGameSocketServiceImpl implements BodyGameSocketService{

    private final BodyGameRoomService bodyGameRoomService;

    @Override
    public BodyGameStartResponseDto initGame(long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto){
        bodyGameRoomService.createBodyGameRoom(roomSequence,bodyGameStartRequestDto);
        BodyGameStartResponseDto bodyGameStartResponseDto = new BodyGameStartResponseDto("ready");
        return bodyGameStartResponseDto;
    }
}
