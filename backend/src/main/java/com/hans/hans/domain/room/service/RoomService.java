package com.hans.hans.domain.room.service;

import com.hans.hans.domain.bodygame.dto.BodyGameCreateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameCreateResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateResponseDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateResponseDto;
import com.hans.hans.global.enumerate.Modes;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    RoomsResponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable);
    RoomMemberResponseDto enterRoom(String email, Long roomSequence);
    RoomMemberResponseDto enterRoomByRandom(String email, Modes modes);
    ConversationCreateResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto);
    WordGameCreateResponseDto createWordGameRoom(String email, WordGameCreateRequestDto wordGameCreateRequestDto);
    BodyGameCreateResponseDto createBodyGameRoom(String email, BodyGameCreateRequestDto bodyGameCreateRequestDto);
    boolean checkEnterRoom(Long roomSequence);
    RoomsResponseDto searchRoomByTitle(String title, Pageable pageable, Modes modes);
    RoomsResponseDto searchRoomByNickname(String nickname, Pageable pageable, Modes modes);
    ConversationUpdateResponseDto updateConversationRoom(Long roomSequence, ConversationUpdateRequestDto conversationUpdateRequestDto);
    WordGameUpdateResponseDto updateWordGameRoom(Long roomSequence, WordGameUpdateRequestDto wordGameUpdateRequestDto);
    BodyGameUpdateResponseDto updateBodyGameRoom(Long roomSequence, BodyGameUpdateRequestDto bodyGameUpdateRequestDto);
    void leaveRoom(String email);
    void updateRoomStatus(Long roomSequence, boolean roomStatus);
}
