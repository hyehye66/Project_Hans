package com.hans.hans.global.exception;

public class NotLoggedInException extends RuntimeException{
    public NotLoggedInException(String message){
        super(message);
    }
}
