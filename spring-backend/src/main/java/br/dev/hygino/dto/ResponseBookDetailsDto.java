package br.dev.hygino.dto;

import br.dev.hygino.models.BookStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseBookDetailsDto(
        UUID id,
        String title,
        String author,
        String personalCode,
        Integer edition,
        String publisher,
        Integer totalPages,
        BookStatus bookStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}