package com.programming.answerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double score;
    private Long attempts;
    @Column(length = 5000)
    private String data;
    private String audioFile;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Boolean isChecked;
    private LocalDateTime dateOfSubmission;
    private Long questionId;
    @ElementCollection
    private List<Long> optionId;
    private Long userId;
    private Long resultId;
}
