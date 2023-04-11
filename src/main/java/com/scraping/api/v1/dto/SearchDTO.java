package com.scraping.api.v1.dto;

import com.scraping.domain.Frequency;
import com.scraping.domain.SearchSource;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class SearchDTO {

    private String userId;

    private SearchSource source;

    @NotNull
    @NotBlank
    private String searchExpression;

    @NotNull
    private Frequency frequency;
}
