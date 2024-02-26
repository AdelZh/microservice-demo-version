package com.programming.answerservice.dto;

import com.programming.answerservice.entity.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ResultRequest {

    private LocalDateTime dateOfSubmission;
    private Boolean isSeen;
    private double score;
    private Status status;
    private List<Long> answerId;
    private Long userId;
}
