package br.dev.hygino.dto;

import br.dev.hygino.entities.Book;

public record BookDTO(
        Long id,
        String title,
        String publisher,
        String isbn,
        Short year,
        Integer leftAmount,
        Integer totalAmount) {

    public BookDTO(Book entity) {
        this(
                entity.getId(),
                entity.getTitle(),
                entity.getPublisher(),
                entity.getIsbn(),
                entity.getReleaseYear(),
                entity.getLeftAmount(),
                entity.getTotalAmount()
            );
    }
}
