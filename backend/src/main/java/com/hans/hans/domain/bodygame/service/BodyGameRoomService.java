package com.hans.hans.domain.bodygame.service;

import com.hans.hans.domain.bodygame.dto.BodyGameStartRequestDto;
import com.hans.hans.domain.bodygame.entity.BodyGameRoom;

public interface BodyGameRoomService {
    public void createBodyGameRoom(Long roomSequence, BodyGameStartRequestDto bodyGameStartRequestDto);
}
