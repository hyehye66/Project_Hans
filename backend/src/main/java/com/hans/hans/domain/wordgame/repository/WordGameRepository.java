package com.hans.hans.domain.wordgame.repository;

import com.hans.hans.domain.wordgame.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordGameRepository extends JpaRepository<Word, Long>{
    List<Word> findAll();
    Word findByWordSequence(Long wordSequence);
}
