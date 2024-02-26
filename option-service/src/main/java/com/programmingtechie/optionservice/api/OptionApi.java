package com.programmingtechie.optionservice.api;

import com.programmingtechie.optionservice.dto.OptionRequest;
import com.programmingtechie.optionservice.entity.Option;
import com.programmingtechie.optionservice.entity.QuestionType;
import com.programmingtechie.optionservice.service.OptionService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/option")
@AllArgsConstructor
public class OptionApi {

    private final OptionService optionService;

    @PostMapping("/saveOption")
    public  List<Long> saveOption(@RequestBody List<OptionRequest> optionRequest,
                                  @RequestParam QuestionType questionType)
            throws BadRequestException {
       return optionService.createOptions(optionRequest, questionType);
    }

    @GetMapping("/getAllOption")
    public List<Long>  getAllOptionId(@RequestParam List<Long> optionId){
        return optionService.getAllOptionId(optionId);
    }

    @GetMapping("/getOption")
    public Option getOption(@RequestParam List<Long> optionId){
        return optionService.getOptionById(optionId);
    }

    @GetMapping("/count")
    public int countTrueOption(@RequestParam QuestionType questionType ,@RequestParam List<Long> optionId){
        return optionService.countTrueOptions(questionType, optionId);
    }
}

