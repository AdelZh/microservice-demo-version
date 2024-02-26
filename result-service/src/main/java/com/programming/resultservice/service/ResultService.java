package com.programming.resultservice.service;

import com.programming.resultservice.dto.ResultRequest;
import com.programming.resultservice.entity.Result;
import com.programming.resultservice.entity.Status;
import com.programming.resultservice.feign.UserInterface;
import com.programming.resultservice.repo.ResultRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepo repo;
    private final UserInterface userInterface;

    public Long createOrUpdateResult(ResultRequest resultRequest) {
        Long userId = resultRequest.getUserId();

        if (exist(userId)) {
            return update(resultRequest);
        } else {
            Result savedResult = repo.save(mapResultRequestToResult(resultRequest));
            return savedResult.getId();
        }
    }

    private Result mapResultRequestToResult(ResultRequest resultRequest) {
        Result result = new Result();
        result.setDateOfSubmission(LocalDateTime.now());
        result.setScore(0);
        result.setStatus(Status.NOT_EVALUATED);
        result.setIsSeen(true);
        result.setAnswerId(resultRequest.getAnswerId());
        result.setUserId(resultRequest.getUserId());
        return result;
    }

    public Long getByUserId(Long userId){
        return repo.findByUserId(userId).getUserId();
    }

    private boolean exist(Long userId){
        return repo.existsByUserId(userId);
    }

    public boolean exist2(Long userId){
        return repo.existsByUserId(userId);
    }

    private Long update(ResultRequest resultRequest) {
        Long userIds = userInterface.getUser(resultRequest.getUserId());

        Result existingResult = repo.findByUserId(userIds);

        existingResult.setDateOfSubmission(LocalDateTime.now());
        existingResult.setScore(0);
        existingResult.setStatus(Status.NOT_EVALUATED);
        existingResult.setIsSeen(true);
        existingResult.setAnswerId(resultRequest.getAnswerId());
        existingResult.setUserId(resultRequest.getUserId());

        Result savedResult = repo.save(existingResult);
        return savedResult.getId();
    }
}