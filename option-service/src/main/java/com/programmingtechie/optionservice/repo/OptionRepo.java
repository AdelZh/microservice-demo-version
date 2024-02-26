package com.programmingtechie.optionservice.repo;

import com.programmingtechie.optionservice.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepo extends JpaRepository<Option, Long> {
}
