package br.dev.hygino;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.models.Category;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public final class BookFactory {

    private static UUID genId(String uuid) {
        return UUID.fromString(uuid);
    }

    private static final Category romance = new Category(
            UUID.fromString("550e8400-e29b-41d4-a716-446655440001"),
            "Romance",
            "Obras literárias centradas em relações amorosas e afetivas.",
            LocalDateTime.parse("2025-01-10T08:30:00"),
            LocalDateTime.parse("2025-01-10T08:30:00")
    );

    private static final Category aventura = new Category(
            UUID.fromString("550e8400-e29b-41d4-a716-446655440006"),
            "Aventura",
            "Obras que apresentam jornadas, desafios, exploração e situações de ação.",
            LocalDateTime.parse("2025-06-18T13:10:00"),
            LocalDateTime.parse("2025-06-18T13:10:00")
    );


    // Iracema
    private static final UUID idWithLoan = genId("eda7e654-e28a-4b56-a2f0-e87c6b3d4c07");

    // A Lord of Rings
    private static final UUID idWithoutLoan = genId("91173563-8b2b-42d9-b934-d3604ff6f68a");

    public static UUID activeLoanId = UUID.fromString("6b04ba62-2d6e-4054-b1f8-ca263f7d5b18");
    public static UUID inactiveLoanId = UUID.fromString("67c07b66-b6c0-4b12-94d6-bb53fed078d9");

    private BookFactory() {
    }

    public static Book createBookEntityAvailable() {
        return new Book(
                activeLoanId,
                "Iracema",
                "José de Alencar",
                "br001",
                2,
                "Principis",
                122,
                BookStatus.AVAILABLE,
                romance,
                LocalDateTime.of(2025, 5, 15, 14, 12, 3),
                null);
    }

    public static Book createBookEntityInUse() {
        return new Book(
                inactiveLoanId,
                "Iracema",
                "José de Alencar",
                "br001",
                2,
                "Principis",
                122,
                BookStatus.IN_USE,
                romance,
                LocalDateTime.of(2025, 5, 15, 14, 12, 3),
                null);
    }

    public static RequestBookDto createNewBookRequest() {
        return new RequestBookDto("O Senhor dos anéis", "J. R. R. Tolkien", "eng01", 1, "Sextante", 1212, UUID.fromString("550e8400-e29b-41d4-a716-446655440001"));
    }

    public static Book createBookEntityWithoutUse() {
        return new Book(
                idWithoutLoan,
                "O Senhor dos anéis",
                "J. R. R. Tolkien",
                "eng01",
                1,
                "Sextante",
                1212,
                BookStatus.AVAILABLE,
                aventura,
                LocalDateTime.of(2025, 5, 15, 14, 12, 3), null);
    }

    public static List<Book> createBookList() {
        return Arrays.asList(
                new Book(
                        idWithLoan,
                        "Iracema",
                        "José de Alencar",
                        "br001",
                        2,
                        "Principis",
                        122,
                        BookStatus.AVAILABLE,
                        romance,
                        LocalDateTime.of(2025, 5, 15, 14, 12, 3),
                        null),

                new Book(genId(
                        "caf19b5a-c0e5-41e4-b37c-8e8193efaa34"),
                        "O Gaúcho",
                        "José de Alencar",
                        "br002",
                        2,
                        "Principis",
                        122,
                        BookStatus.IN_USE,
                        romance,
                        LocalDateTime.of(2025, 5, 15, 14, 12, 3),
                        null),

                new Book(genId(
                        "68c1c2df-eca4-475d-b092-44b4d3d71f7f"),
                        "Dom Casmurro",
                        "Machado de Assis",
                        "br003",
                        1,
                        "Companhia das Letras",
                        256,
                        BookStatus.AVAILABLE,
                        romance,
                        LocalDateTime.of(2025, 6, 10, 9, 30, 0),
                        null),

                new Book(genId(
                        "77da407e-bba7-4b46-9960-c8f297431b09"),
                        "A Moreninha",
                        "Joaquim Manuel de Macedo",
                        "br004",
                        3,
                        "Editora Melhoramentos",
                        198,
                        BookStatus.IN_USE,
                        romance,
                        LocalDateTime.of(2025, 6, 12, 11, 45, 0),
                        null),

                new Book(
                        genId("8e936ba2-7b36-4d14-b415-a6254d207a35"),
                        "Memórias Póstumas",
                        "Machado de Assis",
                        "br005",
                        1,
                        "Penguin Classics",
                        224,
                        BookStatus.AVAILABLE,
                        romance,
                        LocalDateTime.of(2025, 7, 1, 13, 0, 0),
                        null));
    }
}
