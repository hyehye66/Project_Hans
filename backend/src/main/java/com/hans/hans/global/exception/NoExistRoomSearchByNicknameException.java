package com.hans.hans.global.exception;

public class NoExistRoomSearchByNicknameException extends RuntimeException{
    public NoExistRoomSearchByNicknameException(String message){
        super(message);
    }
}
