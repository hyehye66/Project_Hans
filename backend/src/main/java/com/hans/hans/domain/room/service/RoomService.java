package com.hans.hans.domain.room.service;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.room.dto.RoomReponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomReponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable);
    ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto);
}
