package com.scraping.api.v1.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @Email
    @NotBlank
    @NotEmpty
    @NotNull
    private String email;
}
