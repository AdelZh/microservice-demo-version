package com.programmingtechie.optionservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OptionRequest {

    private String optionTitle;
    private String fileUrl;
    private Boolean isTrueOption;

    public OptionRequest(String optionTitle, String fileUrl, Boolean isTrueOption) {
        this.optionTitle = optionTitle;
        this.fileUrl = fileUrl;
        this.isTrueOption = isTrueOption;
    }
}