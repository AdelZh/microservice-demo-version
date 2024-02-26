package com.programmingtechie.questionservice.dto;

import java.util.List;

public record QuestionRequest(
        String title,
        int duration,
        String statement,
        String passage,
        int attempts,
        String correctAnswer,
        String fileUrl,
        List<OptionRequest> option
) {
}