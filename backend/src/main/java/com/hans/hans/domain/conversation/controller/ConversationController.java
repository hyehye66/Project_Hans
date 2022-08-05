package com.hans.hans.domain.conversation.controller;

import com.hans.hans.domain.conversation.dto.RoomGetRequestDto;
import com.hans.hans.domain.conversation.dto.RoomReponseDto;
import com.hans.hans.domain.conversation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;

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
}