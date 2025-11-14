package br.dev.hygino;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class BookFactory {
    private BookFactory() {
    }

    public static Book createBookEntityAvailable() {
        return new Book(
                1L,
                "O Senhor dos anéis",
                "J. R. R. Tolkien",
                "eng01",
                1,
                "Sextante",
                1212,
                BookStatus.AVAILABLE,
                LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
    }

    public static Book createBookEntityInUse() {
        return new Book(
                2L,
                "Iracema",
                "José de Alencar",
                "br001",
                2,
                "Principis",
                122,
                BookStatus.IN_USE,
                LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
    }

    public static RequestBookDto createNewBook() {
        return new RequestBookDto(
                "O Senhor dos anéis",
                "J. R. R. Tolkien",
                "eng01",
                1,
                "Sextante",
                1212);
    }
}
