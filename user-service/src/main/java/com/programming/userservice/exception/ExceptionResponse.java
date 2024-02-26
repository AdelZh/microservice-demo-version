package com.programming.userservice.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String message

) {
}
