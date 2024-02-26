package com.programmingtechie.questionservice.entity;

import com.programmingtechie.questionservice.entity.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_generator")
    @SequenceGenerator(name = "question_generator", sequenceName = "question_seq", allocationSize = 1)
    private Long id;
    private String title;
    private int duration;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private String statement;
    private String passage;
    private int attempts;
    private String correctAnswer;
    private String fileUrl;
    private Boolean enable;
    @ElementCollection
    private List<Long> optionId;
    private Long testId;

    public void setOptionIds(List<Long> optionIds) {
        this.optionId = optionIds;
    }
}
