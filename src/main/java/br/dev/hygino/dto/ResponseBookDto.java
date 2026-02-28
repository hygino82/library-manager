package br.dev.hygino.dto;

import br.dev.hygino.models.Book;

public record ResponseBookDto(
        long id,
        String title,
        String author,
        String code,
        int pages,
        String publisher,
        int edition,
        boolean activeLoan
        ) {

    public ResponseBookDto(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCode(),
                book.getPages(),
                book.getPublisher(),
                book.getEdition(),
                book.isActiveLoan()
        );
    }
}
