package com.hans.hans.domain.room.service;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.room.dto.RoomResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateResponseDto;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomsResponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable);
    RoomMemberResponseDto enterRoom(String email, Long roomSequence);
    ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto);
    WordGameCreateResponseDto createWordGameRoom(String email, WordGameCreateRequestDto wordGameCreateRequestDto);
    boolean checkEnterRoom(Long roomSequence);
    RoomsResponseDto searchRoomByTitle(String title, Pageable pageable);
    RoomsResponseDto searchRoomByNickname(String nickname, Pageable pageable);
    RoomResponseDto updateRoom(Long roomSequence, ConversationUpdateRequestDto conversationUpdateRequestDto);
    void leaveRoom(Long roomSequence, String email);
}
