package com.programming.answerservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SimpleResponse {
    private HttpStatus httpStatus;
    private String message;
    public SimpleResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
