package com.programmingtechie.questionservice.exception;

public class InvalidQuestionException extends RuntimeException{
    public InvalidQuestionException(String message) {
        super(message);
    }
}
