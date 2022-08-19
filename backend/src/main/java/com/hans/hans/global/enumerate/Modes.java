package com.hans.hans.global.enumerate;

public enum  Modes {
    TALK(1,"conversation"),
    WORD(2,"word"),
    BODY(3,"body"),
    LYRICS(4,"lyrics");

    int modeSequence;
    String modeName;

    Modes(int modeSequence, String modeName){
        this.modeSequence = modeSequence;
        this.modeName = modeName;
    }

    public int getModeSequence(){
        return this.modeSequence;
    }

    public String getModeName(){
        return this.modeName;
    }

}
