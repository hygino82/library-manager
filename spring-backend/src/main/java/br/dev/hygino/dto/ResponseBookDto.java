package br.dev.hygino.dto;

import br.dev.hygino.models.Book;

public record ResponseBookDto(
        Long id,
        String title,
        String author,
        String personalCode,
        Integer edition,
        String publisher,
        Integer totalPages,
        String bookStatus
) {

    public ResponseBookDto(Book obj) {
        this(
                obj.getId(),
                obj.getTitle(),
                obj.getAuthor(),
                obj.getPersonalCode(),
                obj.getEdition(),
                obj.getPublisher(),
                obj.getTotalPages(),
                obj.getBookStatus().name()
        );
    }
}
