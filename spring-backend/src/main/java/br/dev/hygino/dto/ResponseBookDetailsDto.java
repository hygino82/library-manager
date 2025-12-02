package br.dev.hygino.dto;

import br.dev.hygino.models.BookStatus;

import java.time.LocalDateTime;

public record ResponseBookDetailsDto(
        Long id,
        String title,
        String author,
        String personalCode,
        Integer edition,
        String publisher,
        Integer totalPages,
        BookStatus bookStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
