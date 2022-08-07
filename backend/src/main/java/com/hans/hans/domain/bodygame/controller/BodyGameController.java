package com.hans.hans.domain.bodygame.controller;

import com.hans.hans.domain.bodygame.dto.BodyGameCreateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameCreateResponseDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateRequestDto;
import com.hans.hans.domain.bodygame.dto.BodyGameUpdateResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/body-game/rooms")
@RequiredArgsConstructor
public class BodyGameController {

    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getBodyGameRooms(@PageableDefault(size = 6) Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(3).build(), pageable);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("몸으로 말해요 게임방 리스트 조회에 성공하였습니다. ", roomsResponseDto));
    }

    @PostMapping()
    public ResponseEntity<?> createBodyGameRoom(HttpServletRequest request, @Valid @RequestBody BodyGameCreateRequestDto bodyGameCreateRequestDto){
        String email = (String)request.getAttribute("email");
        BodyGameCreateResponseDto bodyGameCreateResponseDto = roomService.createBodyGameRoom(email,bodyGameCreateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("몸으로 말해요 게임방 생성에 성공하였습니다.", bodyGameCreateResponseDto));
    }

    @GetMapping("/search-title")
    public ResponseEntity<?> searchBodyGameRoomsByTitle(@RequestParam(value = "title") String title, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByTitle(title, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("몸으로 말해요 게임방 제목으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @GetMapping("/search-nickname")
    public ResponseEntity<?> searchBodyGameRoomsByNickname(@RequestParam(value = "nickname") String nickname, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByNickname(nickname,pageable);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("몸으로 말해요 게임방 닉네임으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @PostMapping("/{room-seq}")
    public ResponseEntity<?> enterBodyGameRoom(HttpServletRequest request, @PathVariable(name = "room-seq") Long roomSequence){
        if(!roomService.checkEnterRoom(roomSequence)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommonResponse.createError("현재 몸으로 말해요 게임방에 들어갈 수 있는 인원이 없습니다."));
        }

        String email = (String)request.getAttribute("email");
        RoomMemberResponseDto roomMemberResponseDto = roomService.enterRoom(email,roomSequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("몸으로 말해요 게임방 입장에 성공하였습니다.",roomMemberResponseDto));
    }

    @PutMapping("/{room-seq}")
    public ResponseEntity<?> updateBodyGameRoom(@PathVariable(name = "room-seq") Long roomSequence, @Valid @RequestBody BodyGameUpdateRequestDto bodyGameUpdateRequestDto){
        BodyGameUpdateResponseDto bodyGameUpdateResponseDto = roomService.updateBodyGameRoom(roomSequence, bodyGameUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("몸으로 말해요 게임방 정보 수정이 완료되었습니다.",bodyGameUpdateResponseDto));
    }

    @DeleteMapping()
    public ResponseEntity<?> leaveBodyGameRoom(HttpServletRequest request){
        String email = (String)request.getAttribute("email");
        roomService.leaveRoom(email);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("몸으로 말해요 게임방 나가기가 완료되었습니다.",null));
    }


}
