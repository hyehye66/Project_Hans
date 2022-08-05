package com.hans.hans.domain.conversation.service;

import com.hans.hans.domain.conversation.dto.RoomGetRequestDto;
import com.hans.hans.domain.conversation.dto.RoomReponseDto;
import com.hans.hans.domain.conversation.entity.Room;
import com.hans.hans.domain.conversation.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    @Override
    public RoomReponseDto getRooms(RoomGetRequestDto roomRequestDto, Pageable pageable) {
        Page<Room> rooms = roomRepository.findRoomsByMode(roomRequestDto.toEntity(), pageable);
        RoomReponseDto roomReponseDto = new RoomReponseDto(rooms);
        return roomReponseDto;
    }

}