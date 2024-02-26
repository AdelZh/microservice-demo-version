package com.programmingtechie.questionservice.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String message

) {
}
