package com.hans.hans.domain.room.dto;

import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import lombok.Getter;

import java.util.Date;

@Getter
public class RoomMemberResponseDto {
    private Room room;
    private Date enterDTTM;

    public RoomMemberResponseDto(RoomMember roomMember){
        this.room = roomMember.getRoom();
        this.enterDTTM = roomMember.getEnterDTTM();
    }
}
