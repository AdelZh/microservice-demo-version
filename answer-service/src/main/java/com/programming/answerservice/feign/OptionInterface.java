package com.programming.answerservice.feign;

import com.programming.answerservice.entity.QuestionType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.swing.text.html.Option;
import java.util.List;

@FeignClient("OPTION-SERVICE")
public interface OptionInterface {

    @GetMapping("/api/option/getAllOption")
    public List<Long> getAllOptionId(@RequestParam List<Long> optionId);

    @GetMapping("/api/option/getOption")
    public Option getOption(@RequestParam List<Long> optionId);

    @GetMapping("/api/option/count")
    public int countTrueOption(@RequestParam QuestionType questionType, @RequestParam List<Long> optionId);
}
