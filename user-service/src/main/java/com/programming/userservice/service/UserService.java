package com.programming.userservice.service;

import com.programming.userservice.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public Long getUserById(Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> {
            String message = "Test is does not exist to add the questions";
            return new EntityNotFoundException(message);
        }).getId();
    }
}

