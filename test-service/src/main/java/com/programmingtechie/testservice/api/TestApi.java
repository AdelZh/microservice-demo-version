package com.programmingtechie.testservice.api;

import com.programmingtechie.testservice.dto.TestRequest;
import com.programmingtechie.testservice.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestApi {

    private final TestService testService;
    private final Environment environment;

    @PostMapping
    public void createTest(@RequestBody TestRequest testRequest) {
        testService.save(testRequest);
    }


    @GetMapping("/generate")
    public Long getTestById(@RequestParam Long testId){
        System.out.println(environment.getProperty("local.server.port"));
        return testService.getTestById(testId);
    }
}
