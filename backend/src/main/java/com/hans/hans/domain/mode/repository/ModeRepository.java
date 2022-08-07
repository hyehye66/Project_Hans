package com.hans.hans.domain.mode.repository;

import com.hans.hans.domain.mode.entity.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeRepository extends JpaRepository<Mode,Long> {
    Mode findByModeSequence(Long modeSequence);
    Mode findByModeName(String modeName);
}