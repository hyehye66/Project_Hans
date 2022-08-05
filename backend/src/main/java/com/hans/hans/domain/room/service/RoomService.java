package com.hans.hans.domain.room.service;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomReponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomReponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable);
    RoomMemberResponseDto enterRoom(String email, Long roomSequence);
    ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto);
    boolean checkEnterRoom(Long roomSequence);
    RoomReponseDto searchRoomByTitle(String title, Pageable pageable);
    RoomReponseDto searchRoomByNickname(String nickname, Pageable pageable);
}
