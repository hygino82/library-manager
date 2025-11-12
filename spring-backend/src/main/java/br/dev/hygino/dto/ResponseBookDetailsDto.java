package br.dev.hygino.dto;

import java.time.LocalDateTime;

import br.dev.hygino.models.Book;

public record ResponseBookDetailsDto(
        Long id,
        String title,
        String author,
        String personalCode,
        Integer edition,
        String publisher,
        Integer totalPages,
        String bookStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public ResponseBookDetailsDto(Book obj) {
        this(
                obj.getId(),
                obj.getTitle(),
                obj.getAuthor(),
                obj.getPersonalCode(),
                obj.getEdition(),
                obj.getPublisher(),
                obj.getTotalPages(),
                obj.getBookStatus().name(),
                obj.getCreatedAt(),
                obj.getUpdatedAt()
        );
    }
}
