package com.programming.answerservice.feign;

import com.programming.answerservice.entity.QuestionType;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {

    @GetMapping("/api/question")
    public Long getQuestionById(@RequestParam Long questionId);

    @GetMapping("/api/question/get")
    public QuestionType getQuestionTypeById(@RequestParam Long questionId);

    @GetMapping("/api/question/getQuestion")
    public TypePatternQuestions.Question getQuestionsById(@RequestParam Long questionId);
}
