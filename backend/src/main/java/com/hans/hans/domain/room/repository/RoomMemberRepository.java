package com.hans.hans.domain.room.repository;

import com.hans.hans.domain.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMemberRepository extends JpaRepository<RoomMember,Long> {
}
