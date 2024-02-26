package com.programmingtechie.testservice.repo;

import com.programmingtechie.testservice.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Long> {
}
