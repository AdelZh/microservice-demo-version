package com.programming.answerservice.feign;

import com.programming.answerservice.dto.ResultRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.transform.Result;
import java.util.List;

@FeignClient("RESULT-SERVICE")
public interface ResultInterface {

    @PostMapping("/api/result/save")
    public Long buildResult(@RequestBody ResultRequest resultRequest);


    @GetMapping("/api/result/findByUserId")
    public Long findByUserId(@RequestParam Long userId);

    @GetMapping("/api/result/isExist")
    public boolean exist2(@RequestParam Long userId);
}
