package com.scraping.api.v1.dto;

import com.scraping.domain.SearchSource;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SearchDTO {

    @NotBlank
    @NotNull
    @NotEmpty
    private String userId;

    private SearchSource source;

    @NotNull
    @NotBlank
    private String searchExpression;
}
