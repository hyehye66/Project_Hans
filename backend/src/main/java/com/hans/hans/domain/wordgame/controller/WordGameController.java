package com.hans.hans.domain.wordgame.controller;

import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.service.RoomService;
import com.hans.hans.domain.wordgame.dto.WordGameCreateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameCreateResponseDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateRequestDto;
import com.hans.hans.domain.wordgame.dto.WordGameUpdateResponseDto;
import com.hans.hans.global.enumerate.Modes;
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
    public ResponseEntity<?> getWordGameRooms(@PageableDefault(size = 8) Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.getRooms(RoomGetRequestDto.builder().modeSequence(Modes.WORD.getModeSequence()).build(), pageable);

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
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByTitle(title, pageable, Modes.WORD);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 제목으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @GetMapping("/search-nickname")
    public ResponseEntity<?> searchWordGameRoomsByNickname(@RequestParam(value = "nickname") String nickname, Pageable pageable){
        RoomsResponseDto roomsResponseDto = roomService.searchRoomByNickname(nickname,pageable, Modes.WORD);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 닉네임으로 검색 조회에 성공하였습니다.", roomsResponseDto));
    }

    @PostMapping("/{room-seq}")
    public ResponseEntity<?> enterWordGameRoom(HttpServletRequest request, @PathVariable(name = "room-seq") Long roomSequence){
        if(!roomService.checkEnterRoom(roomSequence)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommonResponse.createError("현재 낱말게임방에 들어갈 수 있는 인원이 없습니다."));
        }

        String email = (String)request.getAttribute("email");
        RoomMemberResponseDto roomMemberResponseDto = roomService.enterRoom(email,roomSequence);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("낱말게임방 입장에 성공하였습니다.",roomMemberResponseDto));
    }

    @PostMapping("/random")
    public ResponseEntity<?> enterWordGameRoomByRandom(HttpServletRequest request){
        String email = (String)request.getAttribute("email");
        RoomMemberResponseDto roomMemberResponseDto = roomService.enterRoomByRandom(email, Modes.WORD);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 랜덤 입장에 성공하였습니다.",roomMemberResponseDto));
    }

    @PutMapping("/{room-seq}")
    public ResponseEntity<?> updateWordGameRoom(@PathVariable(name = "room-seq") Long roomSequence, @Valid @RequestBody WordGameUpdateRequestDto wordGameUpdateRequestDto){
        WordGameUpdateResponseDto wordGameUpdateResponseDto = roomService.updateWordGameRoom(roomSequence, wordGameUpdateRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.createSuccess("낱말게임방 정보 수정이 완료되었습니다.",wordGameUpdateResponseDto));
    }

    @DeleteMapping("/{room-seq}")
    public ResponseEntity<?> leaveWordGameRoom(HttpServletRequest request){
        String email = (String)request.getAttribute("email");
        roomService.leaveRoom(email);

        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.createSuccess("낱말게임방 나가기가 완료되었습니다.",null));
    }


}
