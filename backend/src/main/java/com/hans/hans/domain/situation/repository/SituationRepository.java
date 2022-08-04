package com.hans.hans.domain.situation.repository;

import com.hans.hans.domain.situation.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationRepository extends JpaRepository<Situation, Long> {
}
