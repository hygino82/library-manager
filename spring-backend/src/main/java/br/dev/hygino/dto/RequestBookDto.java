package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestBookDto(
		@NotBlank @Size(max = 100, min = 3) String title,
		@NotBlank @Size(max = 100, min = 3) String author, 
		@NotBlank @Size(max = 30, min = 3) String personalCode,
		@NotNull Integer edition, 
		@NotBlank @Size(max = 100, min = 3) String publisher, 
		@NotNull Integer totalPages) {
}