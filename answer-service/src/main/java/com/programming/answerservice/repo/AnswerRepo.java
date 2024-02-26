package com.programming.answerservice.repo;

import com.programming.answerservice.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {

}
