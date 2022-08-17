package com.hans.hans.domain.dictation.repository;

import com.hans.hans.domain.dictation.entity.Dictation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictationRepository extends JpaRepository<Dictation, Long> {
}
