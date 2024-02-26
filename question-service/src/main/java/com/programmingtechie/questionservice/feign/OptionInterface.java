package com.programmingtechie.questionservice.feign;

import com.programmingtechie.questionservice.dto.OptionRequest;
import com.programmingtechie.questionservice.entity.enums.QuestionType;
import org.apache.coyote.BadRequestException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("OPTION-SERVICE")
public interface OptionInterface {

    @PostMapping("/api/option/saveOption")
    public List<Long> saveOption(@RequestBody List<OptionRequest> optionRequest,
                                 @RequestParam QuestionType questionType) throws BadRequestException;
}
