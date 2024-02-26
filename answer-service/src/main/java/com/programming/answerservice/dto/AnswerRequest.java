package com.programming.answerservice.dto;

import lombok.Data;
import java.util.List;

@Data
public class AnswerRequest {

    private Long attempts;
    private String input;
    private String audioFile;
    private List<Long> optionId;
    private Long questionID;
    private Long testId;
}

