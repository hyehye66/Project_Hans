package com.hans.hans.domain.conversation.controller;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.room.dto.RoomResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
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
        RoomsResponseDto roomsResponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(1L).build(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("대화방 리스트 조회에 성공하였습니다. ", roomsResponseDto));
    }

    @PostMapping()
    public ResponseEntity<?> createConversationRoom(HttpServletRequest request, @Valid @RequestBody ConversationCreateRequestDto conversationCreateRequestDto){
        String email = (String)request.getAttribute("email");
        RoomResponseDto roomResponseDto = roomService.createConversationRoom(email,conversationCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("대화방 생성에 성공하였습니다.", roomResponseDto));
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

    @GetMapping("/search-title")
    public ResponseEntity<?> searchConversationRoomsByTitle(@RequestParam(value = "title") String title, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByTitle(title, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("대화방 제목으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @GetMapping("/search-nickname")
    public ResponseEntity<?> searchConversationRoomsByNickname(@RequestParam(value = "nickname") String nickname, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByNickname(nickname,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("대화방 닉네임으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @PutMapping("/{room-seq}")
    public ResponseEntity<?> updateConversationRoom(@PathVariable(name = "room-seq") Long roomSequence, @Valid @RequestBody ConversationUpdateRequestDto conversationUpdateRequestDto){
        RoomResponseDto roomResponseDto = roomService.updateRoom(roomSequence, conversationUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(CommonResponse.createSuccess("대화방 정보 수정이 완료되었습니다.",roomResponseDto));
    }


}