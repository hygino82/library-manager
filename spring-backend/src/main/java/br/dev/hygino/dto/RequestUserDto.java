package br.dev.hygino.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestUserDto(
		@NotBlank @Size(max = 100, min = 3) String name,

		@NotBlank(message = "O atributo não deve estar em branco!") String schoolAttribute,

		@Email @Size(max = 100, min = 3) @NotBlank String email,

		@NotBlank @Size(max = 20, min = 8) String phoneNumber){

}
