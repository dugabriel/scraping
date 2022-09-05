package com.scraping.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document("search")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Search extends BaseEntity {

    @Id
    private String id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String userId;

    private SearchSource source;

    @NotBlank
    @NotNull
    @NotEmpty
    private String searchExpression;
}
