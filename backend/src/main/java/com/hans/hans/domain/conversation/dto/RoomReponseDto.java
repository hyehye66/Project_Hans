package com.hans.hans.domain.conversation.dto;

import com.hans.hans.domain.conversation.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
@ToString
public class RoomReponseDto {
    private Page<Room> listRooms;
}