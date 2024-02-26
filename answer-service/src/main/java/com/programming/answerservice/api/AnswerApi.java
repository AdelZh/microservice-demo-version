package com.programming.answerservice.api;

import com.programming.answerservice.dto.AnswerRequest;
import com.programming.answerservice.dto.SimpleResponse;
import com.programming.answerservice.service.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@AllArgsConstructor
public class AnswerApi {

    private final AnswerService answerService;

    @PostMapping
    public SimpleResponse saveAnswer(@RequestParam Long userId, @RequestBody List<AnswerRequest> answerRequest){
        return answerService.saveUserAnswer(userId, answerRequest);
    }
}
