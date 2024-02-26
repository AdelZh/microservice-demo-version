package com.programmingtechie.optionservice.service;

import com.programmingtechie.optionservice.dto.OptionRequest;
import com.programmingtechie.optionservice.entity.Option;
import com.programmingtechie.optionservice.entity.QuestionType;
import com.programmingtechie.optionservice.repo.OptionRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OptionService {

    private final OptionRepo optionRepo;

    public int countTrueOptions(QuestionType questionType, List<Long> optionIds) {
        List<Option> options = optionRepo.findAllById(optionIds);
        int trueOptionCount = 0;

        switch (questionType) {
            case SELECT_MAIN_IDEA:
            case SELECT_THE_BEST_TITLE:
            case LISTEN_AND_SELECT_WORD:
            case SELECT_REAL_ENGLISH_WORD:
                trueOptionCount = (int) options.stream()
                        .filter(Option::getIsTrueOption)
                        .count();
                break;
                default:
                break;
        }
        return trueOptionCount;
    }

    public List<Long> createOptions(List<OptionRequest> optionRequestList, QuestionType questionType) throws BadRequestException {
        List<Option> options = new ArrayList<>();

        int trueOptionCount = 0;

        for (OptionRequest optionRequest : optionRequestList) {
            Option option = Option.builder()
                    .title(optionRequest.getOptionTitle())
                    .isTrueOption(optionRequest.getIsTrueOption())
                    .build();

            if (questionType == QuestionType.LISTEN_AND_SELECT_WORD) {
                option.setFileUrl(optionRequest.getFileUrl());
            }

            if ((questionType == QuestionType.SELECT_THE_BEST_TITLE || questionType == QuestionType.SELECT_MAIN_IDEA)
                    && option.getIsTrueOption()) {
                trueOptionCount++;

                if (trueOptionCount > 1) {
                    throw new BadRequestException("Only one correct answer must be provided!");
                }
            }
            options.add(option);
        }
        return optionRepo.saveAll(options)
                .stream()
                .map(Option::getOptionId)
                .collect(Collectors.toList());
    }

    public List<Long> getAllOptionId(List<Long> optionId) {
        return optionId
                .stream()
                .map(id -> optionRepo.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(Option::getOptionId)
                .collect(Collectors.toList());
    }

    public Option getOptionById(List<Long> optionId) {
        return (Option) optionRepo.findAllById(optionId);
    }

}