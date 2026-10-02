package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestCategoryDto(
		@NotBlank @Size(max = 40) String name, 
		@NotBlank @Size(max = 100) String description) {
}
