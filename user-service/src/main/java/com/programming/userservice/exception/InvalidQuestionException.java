package com.programming.userservice.exception;

public class InvalidQuestionException extends RuntimeException{
    public InvalidQuestionException(String message) {
        super(message);
    }
}
