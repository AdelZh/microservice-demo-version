package com.programmingtechie.testservice.service;

import com.programmingtechie.testservice.dto.TestRequest;
import com.programmingtechie.testservice.entity.Test;
import com.programmingtechie.testservice.repo.TestRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestService {

    private final TestRepo testRepo;

    public void save(TestRequest testRequest) {
        Test test = Test.builder()
                .title(testRequest.title())
                .shortDescription(testRequest.shortDescription())
                .enable(false)
                .build();

        testRepo.save(test);
    }

    public Long getTestById(Long testId) {
        return testRepo.findById(testId).orElseThrow(() -> {
            String message = "Test is does not exist to add the questions";
            return new EntityNotFoundException(message);
        }).getId();
    }
}
