package com.hans.hans.domain.room.dto;

import com.hans.hans.domain.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class RoomsResponseDto {
    private Page<Room> listRooms;
}