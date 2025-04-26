package br.dev.hygino.dto;

import br.dev.hygino.models.SchoolAtribute;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestUserDto(
		@NotBlank @Size(max = 100, min = 3) String name,

		@NotNull SchoolAtribute schoolAtribute,

		@Email @Size(max = 100, min = 3) @NotBlank String email,

		@NotBlank @Size(max = 20, min = 8) String phoneNumber){

}
