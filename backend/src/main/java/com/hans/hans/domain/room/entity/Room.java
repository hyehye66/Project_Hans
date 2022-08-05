package com.hans.hans.domain.room.entity;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ROOMS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_SEQ")
    private Long roomSequence;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "RESTRICT_NUM", nullable = false)
    private int restrictNum;

    @Column(name = "CURRENT_NUM", nullable = false)
    private int currentNum;

    @Column(name = "ROOM_DTTM",nullable = false)
    private Date roomDTTM;

    @Column(name = "ROOM_STATUS",nullable = false)
    private boolean roomStatus;

    @OneToOne
    @JoinColumn(name = "MEMEBER_SEQ")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "MODE_SEQ")
    private Mode mode;

    @Builder
    public Room(Mode mode, String title, int restrictNum, int currentNum, Date roomDTTM, boolean roomStatus, Member member){
        this.mode = mode;
        this.title = title;
        this.restrictNum = restrictNum;
        this.currentNum = currentNum;
        this.roomDTTM = roomDTTM;
        this.roomStatus = roomStatus;
        this.member = member;
    }

    public void updateRoomStatus(boolean roomStatus){
        this.roomStatus = roomStatus;
    }

    public void updateCurrentNum(int currentNum){
        this.currentNum=currentNum;
    }

    public void updateRoomTitleAndRestricNum(String title, int restrictNum){
        this.title = title;
        this.restrictNum = restrictNum;
    }

}