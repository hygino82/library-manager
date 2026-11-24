package br.dev.hygino.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;

public record ResponseBookDetailsDto(
                UUID id,
                String title,
                String author,
                String personalCode,
                Integer edition,
                String publisher,
                Integer totalPages,
                BookStatus bookStatus,
                String category,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

        public ResponseBookDetailsDto(Book book) {
                this(
                                book.getId(),
                                book.getTitle(), book.getAuthor(), book.getPersonalCode(),
                                book.getEdition(), book.getPublisher(), book.getTotalPages(), book.getBookStatus(),
                                book.getCategory().getName(),
                                book.getCreatedAt(), book.getUpdatedAt());
        }
}
