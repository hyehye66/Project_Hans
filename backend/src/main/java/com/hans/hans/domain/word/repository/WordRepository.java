package com.hans.hans.domain.word.repository;

import com.hans.hans.domain.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findAll();
    Word findByWordSequence(Long wordSequence);
    List<Word> findWordsByDifficulty(long difficulty);
}
