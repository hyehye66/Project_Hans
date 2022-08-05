package com.hans.hans.domain.member.repository;


import com.hans.hans.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    //Repository에서 Optional을 반환하는 경우 원하는 값이 있으면 원하는 객체로 받고 없으면 Exception처리를 하는 패턴을 사용
    Optional<Member> findByEmail(String email);
}
