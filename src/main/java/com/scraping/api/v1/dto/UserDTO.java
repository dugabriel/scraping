package com.scraping.api.v1.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String name;
}
