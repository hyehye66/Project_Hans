package com.hans.hans.domain.conversation.Repository;

import com.hans.hans.domain.conversation.entity.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeRepository extends JpaRepository<Mode,Long> {
    Mode findByModeSequence(Long modeSequence);
}