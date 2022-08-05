package com.hans.hans.domain.room.entity;

import com.hans.hans.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ROOM_MEMBERS")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_MEMBERS")
    private Long roomMembersSequence;

    @ManyToOne
    @JoinColumn(name = "MEMBER_SEQ")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ROOM_SEQ")
    private Room room;

    @Column(name = "ENTER_DTTM",nullable = false)
    private Date enterDTTM;

    @Builder
    public RoomMember(Member member, Room room, Date enterDTTM){
        this.member = member;
        this.room = room;
        this.enterDTTM = enterDTTM;
    }
}
