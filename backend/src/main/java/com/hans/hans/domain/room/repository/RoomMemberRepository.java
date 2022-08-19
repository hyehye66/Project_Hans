package com.hans.hans.domain.room.repository;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.room.entity.Room;
import com.hans.hans.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomMemberRepository extends JpaRepository<RoomMember,Long> {
    RoomMember findByMember(Member member);
    List<RoomMember> findRoomMembersByRoomOrderByEnterDTTMAsc(Room room);
    List<RoomMember> findRoomMembersByRoom(Room room);
}
