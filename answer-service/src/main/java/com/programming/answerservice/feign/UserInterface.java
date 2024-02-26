package com.programming.answerservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-SERVICE")
public interface UserInterface {

    @GetMapping("/api/user/get")
    public Long getUser(@RequestParam Long userId);
}
