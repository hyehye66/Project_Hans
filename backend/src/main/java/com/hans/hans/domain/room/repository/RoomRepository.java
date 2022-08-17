package com.hans.hans.domain.room.repository;

import com.hans.hans.domain.member.entity.Member;
import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findRoomsByModeOOrderByRoomDTTMDesc(Mode mode, Pageable pageable);
    Room findByRoomSequence(Long roomSequence);
    Page<Room> findRoomsByTitleContaining(String nickname, Pageable pageable);
    Page<Room> findRoomByMember(Member member, Pageable pageable);
    List<Room> findRoomsByModeAndRoomStatus(Mode mode, boolean roomStatus);
}