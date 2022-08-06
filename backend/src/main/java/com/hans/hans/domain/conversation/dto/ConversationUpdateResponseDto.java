package com.hans.hans.domain.conversation.dto;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import lombok.Getter;

import java.util.Date;

@Getter
public class ConversationUpdateResponseDto {

    private Long roomSequence;
    private Member member;
    private Mode mode;
    private String title;
    private int restrictNum;
    private int currentNum;
    private Date roomDTTM;
    private boolean roomStatus;

    public ConversationUpdateResponseDto(Room room){
        this.roomSequence = room.getRoomSequence();
        this.member = room.getMember();
        this.mode = room.getMode();
        this.title = room.getTitle();
        this.restrictNum = room.getRestrictNum();
        this.currentNum = room.getCurrentNum();
        this.roomDTTM = room.getRoomDTTM();
        this.roomStatus = room.isRoomStatus();
    }
}
