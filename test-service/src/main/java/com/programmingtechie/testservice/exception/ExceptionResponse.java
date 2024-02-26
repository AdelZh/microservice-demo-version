package com.programmingtechie.testservice.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
        String message

) {
}
