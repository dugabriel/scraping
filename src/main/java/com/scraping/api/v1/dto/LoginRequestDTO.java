package com.scraping.api.v1.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
	@NotBlank
 	private String username;

	@NotBlank
	private String password;
}
