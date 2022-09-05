package com.scraping.api.v1.dto;

import com.scraping.domain.SearchSource;
import lombok.Data;

@Data
public class SearchDTO {

    private String userId;

    private SearchSource source;

    private SearchSource searchExpression;
}
