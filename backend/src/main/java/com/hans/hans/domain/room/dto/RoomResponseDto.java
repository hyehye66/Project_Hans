package com.hans.hans.domain.room.dto;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.member.entity.Member;
import lombok.Getter;

import java.util.Date;

@Getter
public class RoomResponseDto {

    private Long roomSequence;
    private Member member;
    private Mode mode;
    private String title;
    private int restricNum;
    private int currentNum;
    private Date roomDTTM;
    private boolean roomStatus;

    public RoomResponseDto(Room room){
        this.roomSequence = room.getRoomSequence();
        this.member = room.getMember();
        this.mode = room.getMode();
        this.title = room.getTitle();
        this.restricNum = room.getRestrictNum();
        this.currentNum = room.getCurrentNum();
        this.roomDTTM = room.getRoomDTTM();
        this.roomStatus = room.isRoomStatus();
    }

}
