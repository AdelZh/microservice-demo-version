package com.programmingtechie.questionservice.service;

import com.programmingtechie.questionservice.dto.QuestionRequest;
import com.programmingtechie.questionservice.dto.SimpleResponse;
import com.programmingtechie.questionservice.entity.Question;
import com.programmingtechie.questionservice.entity.enums.QuestionType;
import com.programmingtechie.questionservice.feign.OptionInterface;
import com.programmingtechie.questionservice.feign.TestInterface;
import com.programmingtechie.questionservice.repo.QuestionRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepo questionRepo;
    @Autowired
    TestInterface testInterface;
    @Autowired
    OptionInterface optionInterface;

    public SimpleResponse createQuestion(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        switch (questionType) {
            case SELECT_REAL_ENGLISH_WORD,
                    LISTEN_AND_SELECT_WORD,
                    SELECT_MAIN_IDEA,
                    SELECT_THE_BEST_TITLE -> {
                createQuestionWithOptions(test, questionType, questionRequest);
            }
            case RECORD_SAYING -> {
                createRecordSayingStatement(test, questionType, questionRequest);
            }
            case TYPE_WHAT_YOU_HEAR -> {
                createTypeWhatYouHear(test, questionType, questionRequest);
            }
            case DESCRIBE_IMAGE -> {
                createDescribeImage(test, questionType, questionRequest);
            }
            case HIGHLIGHTS_THE_ANSWER -> {
                highlightTheAnswer(test, questionType, questionRequest);
            }
            case RESPOND_IN_AT_LEAST_N_WORDS -> {
                respondInAtLeastNWords(test, questionType, questionRequest);
            }
            default -> throw new BadRequestException("Question saving failed");
        }
        return new SimpleResponse(HttpStatus.OK, "Question saved successfully");
    }

    @SneakyThrows
    private void createQuestionWithOptions(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .duration(questionRequest.duration())
                .questionType(questionType)
                .enable(false)
                .testId(tests)
                .build();

        List<Long> optionIds = optionInterface.saveOption(questionRequest.option(), questionType);

        question.setOptionIds(optionIds);
        questionRepo.save(question);

    }

    private void respondInAtLeastNWords(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .questionType(questionType)
                .duration(questionRequest.duration())
                .attempts(questionRequest.attempts())
                .statement(questionRequest.statement())
                .enable(false)
                .testId(tests)
                .build();
        questionRepo.save(question);
    }

    private void createTypeWhatYouHear(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .questionType(questionType)
                .duration(questionRequest.duration())
                .correctAnswer(questionRequest.correctAnswer())
                .fileUrl(questionRequest.fileUrl())
                .attempts(questionRequest.attempts())
                .enable(false)
                .testId(tests)
                .build();
        questionRepo.save(question);
    }

    private void highlightTheAnswer(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .questionType(questionType)
                .duration(questionRequest.duration())
                .correctAnswer(questionRequest.correctAnswer())
                .passage(questionRequest.passage())
                .enable(false)
                .testId(tests)
                .build();
        questionRepo.save(question);
    }

    private void createDescribeImage(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .duration(questionRequest.duration())
                .questionType(questionType)
                .fileUrl(questionRequest.fileUrl())
                .correctAnswer(questionRequest.correctAnswer())
                .enable(false)
                .testId(tests)
                .build();
        questionRepo.save(question);
    }

    private void createRecordSayingStatement(Long test, QuestionType questionType, QuestionRequest questionRequest) {
        Long tests = testInterface.getTestById(test);
        Question question = Question.builder()
                .title(questionRequest.title())
                .questionType(questionType)
                .statement(questionRequest.statement())
                .correctAnswer(questionRequest.correctAnswer())
                .duration(questionRequest.duration())
                .enable(false)
                .testId(tests)
                .build();
        questionRepo.save(question);
    }

    public SimpleResponse updateQuestionBoolean(Long questionId, Boolean isEnable) {
        Question question = questionRepo.findById(questionId).orElseThrow(() -> {
            log.warn(String.format("Question with: " + questionId + " is not found"));
            return new NotFoundException("Question with: " + questionId + " is not found");
        });

        question.setEnable(isEnable);
        questionRepo.save(question);
        log.info("Question with ID {} successfully updated (isEnable={})", questionId, isEnable);
        return new SimpleResponse(HttpStatus.OK, "Successfully updated");
    }

    public Long getQuestionById(Long questionId) {
        return questionRepo.findById(questionId).orElseThrow(() -> {
            String message = "Test is does not exist to add the questions";
            return new EntityNotFoundException(message);
        }).getId();
    }

    public QuestionType getQuestionTypeById(Long questionId) {
        Question question = questionRepo.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found with ID: " + questionId));

        return question.getQuestionType();
    }

    public Question getQuestionsById(Long questionId) {
        return questionRepo.findById(questionId)
                .orElseThrow(() -> new NotFoundException("Question not found with ID: " + questionId));
    }
}
