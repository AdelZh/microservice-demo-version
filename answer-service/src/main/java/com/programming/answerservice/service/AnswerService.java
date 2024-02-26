package com.programming.answerservice.service;

import com.programming.answerservice.dto.AnswerRequest;
import com.programming.answerservice.dto.ResultRequest;
import com.programming.answerservice.dto.SimpleResponse;
import com.programming.answerservice.entity.Answer;
import com.programming.answerservice.entity.QuestionType;
import com.programming.answerservice.entity.Status;
import com.programming.answerservice.feign.OptionInterface;
import com.programming.answerservice.feign.QuestionInterface;
import com.programming.answerservice.feign.ResultInterface;
import com.programming.answerservice.feign.UserInterface;
import com.programming.answerservice.repo.AnswerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

import static com.programming.answerservice.entity.QuestionType.*;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerService {

    private final QuestionInterface questionInterface;
    private final OptionInterface optionInterface;
    private final AnswerRepo answerRepo;
    private final ResultInterface resultInterface;
    private final UserInterface userInterface;

    private static final EnumSet<QuestionType> QUESTION_TYPES_REQUIRING_DATA = EnumSet.of(
            QuestionType.DESCRIBE_IMAGE,
            QuestionType.RESPOND_IN_AT_LEAST_N_WORDS,
            QuestionType.HIGHLIGHTS_THE_ANSWER,
            QuestionType.TYPE_WHAT_YOU_HEAR
    );

    public SimpleResponse saveUserAnswer(Long userId, List<AnswerRequest> answerRequest) {
        for (AnswerRequest answerRequest1 : answerRequest) {
            Long question = questionInterface.getQuestionById(answerRequest1.getQuestionID());


            if (answerRequest1.getQuestionID() != null) {
                List<Long> options = optionInterface.getAllOptionId(answerRequest1.getOptionId());

                Answer answer = new Answer();
                answer.setQuestionId(question);
                answer.setUserId(userId);
                answer.setStatus(Status.NOT_EVALUATED);
                answer.setOptionId(options);
                answer.setDateOfSubmission(LocalDateTime.now());

                QuestionType questionType = questionInterface.getQuestionTypeById(answerRequest1.getQuestionID());
                if (QUESTION_TYPES_REQUIRING_DATA.contains(questionType)) {
                    answer.setData(answerRequest1.getInput());
                }

                if (answerRequest1.getAttempts() != null) {
                    answer.setAttempts(answerRequest1.getAttempts());
                }
                if (answerRequest1.getAudioFile() != null && questionType.equals(RECORD_SAYING)) {
                    answer.setAudioFile(String.valueOf(Collections.singletonList(answerRequest1.getAudioFile())));
                }
                if (questionType == QuestionType.SELECT_MAIN_IDEA ||
                        questionType == QuestionType.SELECT_THE_BEST_TITLE) {
                    if (options.size() != 1) {
                        log.warn("For this question, you can choose only one option.");
                        throw new IllegalArgumentException("For this question, you can choose only one option.");
                    }
                }
                answerRepo.save(answer);
                createResultAndCheckAnswer(userId, Collections.singletonList(answer));
            }
            log.info("Answer for question ID {} successfully saved",
                    answerRequest1.getQuestionID());
        }
        return new SimpleResponse(HttpStatus.CREATED, "Answers are successfully created!");
    }


    public void createResultAndCheckAnswer(Long userId, List<Answer> answers) {
        Set<QuestionType> evaluatedTypesBySystem = Set.of(
                QuestionType.SELECT_REAL_ENGLISH_WORD,
                QuestionType.LISTEN_AND_SELECT_WORD,
                QuestionType.SELECT_MAIN_IDEA,
                QuestionType.SELECT_THE_BEST_TITLE
        );

        boolean exist = resultInterface.exist2(userId);

        for (Answer answer : answers) {
            QuestionType questionType = questionInterface.getQuestionTypeById(answer.getQuestionId());

            if (evaluatedTypesBySystem.contains(questionType)) {
                List<Long> options = optionInterface.getAllOptionId(answer.getOptionId());
                int selectedOptionsCount = answer.getOptionId().size();
                answer.setStatus(Status.EVALUATED);
                answer.setIsChecked(true);

                if (selectedOptionsCount == options.size()) {
                    answer.setScore(0);
                } else {
                    int selectedCorrectOptionsCount = optionInterface.countTrueOption(questionType, answer.getOptionId());

                    switch (questionType) {
                        case SELECT_REAL_ENGLISH_WORD:
                        case LISTEN_AND_SELECT_WORD:
                            switch (selectedCorrectOptionsCount) {
                                case 1:
                                    answer.setScore(4);
                                    break;
                                case 2:
                                    answer.setScore(6);
                                    break;
                                case 3:
                                    answer.setScore(8);
                                    break;
                                case 4:
                                    answer.setScore(10);
                                    break;
                                default:
                                    answer.setScore(0);
                                    break;
                            }
                            break;
                        case SELECT_MAIN_IDEA:
                        case SELECT_THE_BEST_TITLE:
                            if (selectedCorrectOptionsCount == 1) {
                                answer.setScore(10);
                            } else {
                                answer.setScore(0);
                            }
                            break;
                        default:
                            answer.setScore(0);
                            break;
                    }
                }

                if (!exist) {
                    ResultRequest resultRequest = ResultRequest.builder()
                            .userId(userId)
                            .build();
                    Long resultResponse = resultInterface.buildResult(resultRequest);
                    answer.setResultId(resultResponse);
                } else {
                    Long result = resultInterface.findByUserId(userId);
                    answer.setResultId(result);
                }
                answerRepo.save(answer);
            }
        }
    }
}