package com.programming.resultservice.repo;

import com.programming.resultservice.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Long> {
    boolean existsByUserId(Long userId);
    Result findByUserId(Long userId);
}
