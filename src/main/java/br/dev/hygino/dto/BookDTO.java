package br.dev.hygino.dto;

import br.dev.hygino.entities.Book;

public record BookDTO(
                Long id,
                String title,
                String author,
                String publisher,
                Integer year,
                Integer amount,
                String location,
                String isbn,
                String gender) {

        public BookDTO(Book entity) {
                this(
                        entity.getId(),
                        entity.getTitle(),
                        entity.getAuthor(),
                        entity.getPublisher(),
                        entity.getReleaseYear(),
                        entity.getAmount(),
                        entity.getLocation(),
                        entity.getIsbn(),
                        entity.getGender()
                );
        }
}
