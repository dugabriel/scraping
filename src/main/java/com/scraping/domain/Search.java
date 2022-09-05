package com.scraping.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("search")
@Data
public class Search {

    @Id
    private String id;

    private String userId;

    private SearchSource source;

    private String searchExpression;
}
