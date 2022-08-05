package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/word-game/rooms")
@RequiredArgsConstructor
public class WordGameController {

    private final RoomService roomService;

    @GetMapping()
    public ResponseEntity<?> getWordGameRooms(@PageableDefault(size = 6) Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(2L).build(), pageable);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임 리스트 조회에 성공하였습니다. ", roomsResponseDto));
    }
}
