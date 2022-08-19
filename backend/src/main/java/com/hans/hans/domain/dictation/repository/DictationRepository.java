package com.hans.hans.domain.dictation.repository;

import com.hans.hans.domain.dictation.entity.Dictation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictationRepository extends JpaRepository<Dictation, Long> {
    List<Dictation> findDictationsByDifficulty(Long difficulty);
}
