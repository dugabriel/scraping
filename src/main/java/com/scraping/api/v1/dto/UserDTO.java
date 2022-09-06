package com.scraping.api.v1.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
