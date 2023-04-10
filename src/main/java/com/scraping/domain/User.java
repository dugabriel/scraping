package com.scraping.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document("user")
@Data
public class User extends BaseEntity {

    @Id
    private String id;

    private String name;

    @NotNull
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
