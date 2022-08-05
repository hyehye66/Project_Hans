package com.hans.hans.domain.conversation.service;

import com.hans.hans.domain.conversation.dto.RoomGetRequestDto;
import com.hans.hans.domain.conversation.dto.RoomReponseDto;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomReponseDto getRooms(RoomGetRequestDto roomRequestDto, Pageable pageable);
}