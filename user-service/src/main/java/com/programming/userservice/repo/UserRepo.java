package com.programming.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.programming.userservice.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
