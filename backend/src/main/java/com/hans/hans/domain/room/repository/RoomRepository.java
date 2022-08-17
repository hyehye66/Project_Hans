package com.hans.hans.domain.room.repository;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findRoomsByModeOrderByRoomDTTMDesc(Mode mode, Pageable pageable);
    Room findByRoomSequence(Long roomSequence);
    Page<Room> findRoomsByTitleContainingAndMode(String nickname, Pageable pageable, Mode mode);
    Page<Room> findRoomByMemberAndMode(Member member, Pageable pageable, Mode mode);
    List<Room> findRoomsByModeAndRoomStatus(Mode mode, boolean roomStatus);
}