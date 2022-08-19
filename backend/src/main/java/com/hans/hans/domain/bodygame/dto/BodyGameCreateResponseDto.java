package com.hans.hans.domain.bodygame.dto;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import lombok.Getter;

import java.util.Date;

@Getter
public class BodyGameCreateResponseDto {
    private Long roomSequence;
    private Member member;
    private Mode mode;
    private String title;
    private int restrictNum;
    private int currentNum;
    private int totalQuestion;
    private Long difficulty;
    private int timeLimit;
    private Date roomDTTM;
    private boolean roomStatus;
    String token;
    public void updateSettings(int totalQuestion, Long difficulty, int timeLimit){
        this.totalQuestion = totalQuestion;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
    }

    public BodyGameCreateResponseDto(Room room, String token){
        this.roomSequence = room.getRoomSequence();
        this.member = room.getMember();
        this.mode = room.getMode();
        this.title = room.getTitle();
        this.restrictNum = room.getRestrictNum();
        this.currentNum = room.getCurrentNum();
        this.roomDTTM = room.getRoomDTTM();
        this.roomStatus = room.isRoomStatus();
        this.token = token;
    }
}
