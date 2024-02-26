package com.programming.resultservice.entity;

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
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateOfSubmission;
    private Boolean isSeen;
    private double score;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    private List<Long> testId;
    private Long userId;
    @ElementCollection
    private List<Long> answerId;

}
