package com.hans.hans.domain.room.repository;

import com.hans.hans.domain.mode.entity.Mode;
import com.hans.hans.domain.room.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findRoomsByMode(Mode mode, Pageable pageable);

}