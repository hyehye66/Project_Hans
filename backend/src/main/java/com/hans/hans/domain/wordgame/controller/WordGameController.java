package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.wordgame.dto.WordGameCreateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateResponseDto;
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
@RequestMapping("/api/word-game/rooms")
@RequiredArgsConstructor
public class WordGameController {

    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getWordGameRooms(@PageableDefault(size = 6) Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(2L).build(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 리스트 조회에 성공하였습니다. ", roomsResponseDto));
    }

    @PostMapping()
    public ResponseEntity<?> createWordGameRoom(HttpServletRequest request, @Valid @RequestBody WordGameCreateRequestDto wordGameCreateRequestDto){
        String email = (String)request.getAttribute("email");
        WordGameCreateResponseDto wordGameCreateResponseDto = roomService.createWordGameRoom(email,wordGameCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("낱말게임방 생성에 성공하였습니다.", wordGameCreateResponseDto));
    }

    @GetMapping("/search-title")
    public ResponseEntity<?> searchWordGameRoomsByTitle(@RequestParam(value = "title") String title, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByTitle(title, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 제목으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @GetMapping("/search-nickname")
    public ResponseEntity<?> searchWordGameRoomsByNickname(@RequestParam(value = "nickname") String nickname, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByNickname(nickname,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 닉네임으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }
}
