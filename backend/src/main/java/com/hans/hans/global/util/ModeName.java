package com.hans.hans.global.util;

public enum ModeName {
    CONVERSATION("conversation"),WORD("word"),BODY("body");
    String name;
    ModeName(String name){
        this.name = name();
    }
}
