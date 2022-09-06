package com.scraping.service.vo;

import com.scraping.domain.SearchSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapingVO {

    private String url;

    private SearchSource source;

    private String userId;
}
