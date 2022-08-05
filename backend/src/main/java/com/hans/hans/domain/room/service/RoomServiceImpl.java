package com.hans.hans.domain.room.service;

import com.hans.hans.domain.conversation.dto.ConversationUpdateRequestDto;
import com.hans.hans.domain.mode.repository.ModeRepository;
import com.hans.hans.domain.conversation.dto.ConversationCreateRequestDto;
import com.hans.hans.domain.room.dto.RoomResponseDto;
import com.hans.hans.domain.room.dto.RoomMemberResponseDto;
import com.hans.hans.domain.room.dto.RoomsResponseDto;
import com.hans.hans.domain.room.dto.RoomGetRequestDto;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import com.hans.hans.domain.room.repository.RoomMemberRepository;
import com.hans.hans.domain.room.repository.RoomRepository;
import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.member.repository.MemberRepository;
import com.hans.hans.global.exception.NoExistMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;
    private final ModeRepository modeRepository;
    private final RoomMemberRepository roomMemberRepository;

    @Override
    public RoomsResponseDto getRooms(RoomGetRequestDto roomGetRequestDto, Pageable pageable) {
        Page<Room> rooms = roomRepository.findRoomsByMode(roomGetRequestDto.toEntity(), pageable);
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);
        return roomsResponseDto;
    }

    @Override
    public boolean checkEnterRoom(Long roomSequence){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        if(room.getRestrictNum()==room.getCurrentNum()) return false;
        return true;
    }

    @Override
    public RoomMemberResponseDto enterRoom(String email, Long roomSequence){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));
        Room room = roomRepository.findByRoomSequence(roomSequence);

        Date now = new Date();

        room.updateCurrentNum(room.getCurrentNum()+1);
        roomRepository.save(room);

        RoomMember roomMember = roomMemberRepository.save(
                RoomMember.builder()
                        .member(member)
                        .room(room)
                        .enterDTTM(now)
                        .build()
        );

        RoomMemberResponseDto roomMemberResponseDto = new RoomMemberResponseDto(roomMember);
        return roomMemberResponseDto;
    }

    @Override
    public RoomsResponseDto searchRoomByTitle(String title, Pageable pageable){
        Page<Room> rooms = roomRepository.findRoomsByTitleContaining(title, pageable);
        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);
        return roomsResponseDto;
    }

    @Override
    public RoomsResponseDto searchRoomByNickname(String nickname, Pageable pageable){
        Member member = memberRepository.findByNickname(nickname);
        Page<Room> rooms = roomRepository.findRoomsByMember(member,pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto(rooms);
        return roomsResponseDto;
    }

    @Override
    public RoomResponseDto createConversationRoom(String email, ConversationCreateRequestDto conversationCreateRequestDto) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NoExistMemberException("존재하는 회원정보가 없습니다."));

        Date now = new Date();
        String title = conversationCreateRequestDto.getTitle();
        int restrictNum = conversationCreateRequestDto.getRestrictNum();

        Room room = roomRepository.save(
                Room.builder()
                        .member(member)
                        .mode(modeRepository.findByModeSequence(1L))
                        .title(title)
                        .restrictNum(restrictNum)
                        .currentNum(1)
                        .roomDTTM(now)
                        .roomStatus(false)
                        .build());

        roomMemberRepository.save(
                RoomMember.builder()
                        .member(member)
                        .room(room)
                        .enterDTTM(now)
                        .build()
        );

        RoomResponseDto roomResponseDto = new RoomResponseDto(room);

        return roomResponseDto;
    }

    @Override
    public RoomResponseDto updateRoom(Long roomSequence, ConversationUpdateRequestDto conversationUpdateRequestDto){
        Room room = roomRepository.findByRoomSequence(roomSequence);
        room.updateRoomTitleAndRestricNum(conversationUpdateRequestDto.getTitle(), conversationUpdateRequestDto.getRestricNum());
        RoomResponseDto roomResponseDto = new RoomResponseDto(roomRepository.save(room));

        return roomResponseDto;

    }

}
