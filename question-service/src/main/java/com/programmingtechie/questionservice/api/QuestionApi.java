package com.programmingtechie.questionservice.api;

import com.programmingtechie.questionservice.dto.QuestionRequest;
import com.programmingtechie.questionservice.dto.SimpleResponse;
import com.programmingtechie.questionservice.entity.Question;
import com.programmingtechie.questionservice.entity.enums.QuestionType;
import com.programmingtechie.questionservice.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@AllArgsConstructor
public class QuestionApi {

    private final QuestionService questionService;

    @PostMapping
    public SimpleResponse createQuestion(@RequestParam Long testId,
                                         @RequestParam QuestionType questionType,
                                         @RequestBody QuestionRequest questionRequest){
        return questionService.createQuestion(testId, questionType, questionRequest);
    }

    @PutMapping
    public SimpleResponse updateQuestionBoolean(@RequestParam Long questionId,
                                                @RequestParam Boolean isEnable){
        return questionService.updateQuestionBoolean(questionId, isEnable);
    }

    @GetMapping
    public Long getQuestionById(@RequestParam Long questionId){
        return questionService.getQuestionById(questionId);
    }

    @GetMapping("/get")
    public QuestionType getQuestionTypeById(@RequestParam Long questionId){
        return questionService.getQuestionTypeById(questionId);
    }

    @GetMapping("/getQuestion")
    public Question getQuestionsById(@RequestParam Long questionId){
        return questionService.getQuestionsById(questionId);

    }
}
