package br.dev.hygino;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;

public class BookFactory {

    public static Book createUserEntity() {
        return new Book(
                1L,
                "O Senhor dos anéis",
                "J. R. R. Tolkien",
                "eng01",
                1,
                "Sextante",
                1212,
                BookStatus.AVALIABLE);
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
