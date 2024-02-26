package com.programmingtechie.questionservice.repo;


import com.programmingtechie.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
