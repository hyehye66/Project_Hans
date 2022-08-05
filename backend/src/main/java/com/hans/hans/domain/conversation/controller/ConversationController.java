package com.hans.hans.domain.conversation.controller;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomReponseDto;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/conversations/rooms")
@RequiredArgsConstructor
public class ConversationController {
    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getConversationRooms(@PageableDefault(size = 6) Pageable pageable){
        RoomReponseDto roomReponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(1L).build(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("대화방 리스트 조회에 성공하였습니다. ",roomReponseDto));
    }

    @PostMapping()
    public ResponseEntity<?> createConversationRoom(HttpServletRequest request, @Valid @RequestBody ConversationCreateRequestDto conversationCreateRequestDto){
        String email = (String)request.getAttribute("email");
        ConversationCreateResponseDto conversationCreateResponseDto = roomService.createConversationRoom(email,conversationCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("대화방 생성에 성공하였습니다.",conversationCreateResponseDto));
    }

    @PostMapping("/{room-seq}")
    public ResponseEntity<?> enterConversationRoom(HttpServletRequest request, @PathVariable(name = "room-seq") Long roomSequence){
        if(!roomService.checkEnterRoom(roomSequence)) {
            return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createError("현재 대화방에 들어갈 수 있는 인원이 없습니다."));
        }

        String email = (String)request.getAttribute("email");
        RoomMemberResponseDto roomMemberResponseDto = roomService.enterRoom(email,roomSequence);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("대화방 입장에 성공하였습니다.",roomMemberResponseDto));
    }




}