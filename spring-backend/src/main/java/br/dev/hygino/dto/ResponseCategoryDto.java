package br.dev.hygino.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.hygino.models.Category;

public record ResponseCategoryDto(
		UUID id, 
		String name, 
		String description, 
		LocalDateTime createdAt,
		LocalDateTime updatedAt) {

	public ResponseCategoryDto(Category category) {
		this(
				category.getId(), 
				category.getName(), 
				category.getDescription(), 
				category.getCreatedAt(),
				category.getUpdatedAt());
	}
}
