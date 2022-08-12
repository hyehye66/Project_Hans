package com.hans.hans.domain.bodygame.dto;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import lombok.Getter;

import java.util.Date;

@Getter
public class BodyGameUpdateResponseDto {
    private Long roomSequence;
    private Member member;
    private Mode mode;
    private String title;
    private int restrictNum;
    private int currentNum;
    private int problemNum;
    private Long difficulty;
    private int timeLimit;
    private Date roomDTTM;
    private boolean roomStatus;

    public void updateSettings(int problemNum, Long difficulty, int timeLimit){
        this.problemNum = problemNum;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
    }

    public BodyGameUpdateResponseDto(Room room){
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
