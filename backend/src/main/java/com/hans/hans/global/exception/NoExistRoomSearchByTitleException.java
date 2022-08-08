package com.hans.hans.global.exception;

public class NoExistRoomSearchByTitleException extends RuntimeException{
    public NoExistRoomSearchByTitleException(String message){
        super(message);
    }
}
