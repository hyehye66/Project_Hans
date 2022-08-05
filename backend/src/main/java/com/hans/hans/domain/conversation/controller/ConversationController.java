package com.hans.hans.domain.conversation.controller;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationCreateResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomReponseDto;
import com.hans.hans.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
public class ConversationController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<?> getConversationRooms(@PageableDefault(size = 6) Pageable pageable){
        RoomReponseDto roomReponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(1L).build(), pageable);
        return ResponseEntity.ok(roomReponseDto);
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createConversationRooms(HttpServletRequest request, @Valid @RequestBody ConversationCreateRequestDto conversationCreateRequestDto){
//        String email = (String)request.getAttribute("email");
        String email = "syi0511@naver.com";
        ConversationCreateResponseDto conversationCreateResponseDto = roomService.createConversationRoom(email,conversationCreateRequestDto);
        return ResponseEntity.ok(conversationCreateResponseDto);
    }
}