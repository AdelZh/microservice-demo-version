package com.programmingtechie.questionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("TEST-SERVICE")
public interface TestInterface {

    @GetMapping("/api/test/generate")
    public Long getTestById(@RequestParam Long testId);

}
