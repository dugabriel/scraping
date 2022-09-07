package com.scraping.service.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScrapingResultVO {

    private String link;

    private String price;

    private String name;

    private String imageUrl;
}
