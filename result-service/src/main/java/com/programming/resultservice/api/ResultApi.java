package com.programming.resultservice.api;

import com.programming.resultservice.dto.ResultRequest;
import com.programming.resultservice.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
@AllArgsConstructor
public class ResultApi {

    private final ResultService resultService;


    @PostMapping("/save")
    public Long buildResult(@RequestBody ResultRequest resultRequest){
        return resultService.createOrUpdateResult(resultRequest);
    }

    @PatchMapping("/update")
    public Long update(@RequestParam ResultRequest resultRequest){
        return resultService.createOrUpdateResult(resultRequest);
    }

    @GetMapping("/findByUserId")
    public Long findByUserId(@RequestParam Long userId){
        return resultService.getByUserId(userId);
    }

    @GetMapping("/isExist")
    public boolean exist2(@RequestParam Long userId){
        return resultService.exist2(userId);
    }
}
