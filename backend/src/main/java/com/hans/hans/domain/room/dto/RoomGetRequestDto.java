package com.hans.hans.domain.room.dto;

import com.hans.hans.domain.mode.entity.Mode;
import lombok.Builder;

public class RoomGetRequestDto {
    private int modeSequence;
    private String modeName;

    @Builder
    public RoomGetRequestDto(int modeSequence, String modeName){
        this.modeSequence = modeSequence;
        this.modeName = modeName;
    }

    public Mode toEntity(){
        return Mode.builder()
                .modeSequence(this.modeSequence)
                .build();
    }
}