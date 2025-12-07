package br.dev.hygino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;

public final class BookFactory {

	private static UUID genId(String uuid) {
		return UUID.fromString(uuid);
	}

	// Iracema
	private static UUID idWithLoan = genId("eda7e654-e28a-4b56-a2f0-e87c6b3d4c07");

	// A Lord of Rings
	private static UUID idWithoutLoan = genId("91173563-8b2b-42d9-b934-d3604ff6f68a");

	private BookFactory() {
	}

	public static Book createBookEntityAvailable() {
		return new Book(idWithoutLoan, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.AVAILABLE,
				LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static Book createBookEntityInUse() {
		return new Book(idWithLoan, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.IN_USE,
				LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static RequestBookDto createNewBookRequest() {
		return new RequestBookDto("O Senhor dos anéis", "J. R. R. Tolkien", "eng01", 1, "Sextante", 1212);
	}

	public static Book createBookEntityWithoutUse() {
		return new Book(idWithoutLoan, "O Senhor dos anéis", "J. R. R. Tolkien", "eng01", 1, "Sextante", 1212,
				BookStatus.AVAILABLE, LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static List<Book> createBookList() {
		return Arrays.asList(
				new Book(idWithLoan, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.AVAILABLE,
						LocalDateTime.of(2025, 5, 15, 14, 12, 3), null),
				new Book(genId("caf19b5a-c0e5-41e4-b37c-8e8193efaa34"), "O Gaúcho", "José de Alencar", "br002", 2,
						"Principis", 122, BookStatus.IN_USE, LocalDateTime.of(2025, 5, 15, 14, 12, 3), null),
				new Book(genId("68c1c2df-eca4-475d-b092-44b4d3d71f7f"), "Dom Casmurro", "Machado de Assis", "br003", 1,
						"Companhia das Letras", 256, BookStatus.AVAILABLE, LocalDateTime.of(2025, 6, 10, 9, 30, 0),
						null),
				new Book(genId("77da407e-bba7-4b46-9960-c8f297431b09"), "A Moreninha", "Joaquim Manuel de Macedo",
						"br004", 3, "Editora Melhoramentos", 198, BookStatus.IN_USE,
						LocalDateTime.of(2025, 6, 12, 11, 45, 0), null),
				new Book(genId("8e936ba2-7b36-4d14-b415-a6254d207a35"), "Memórias Póstumas", "Machado de Assis",
						"br005", 1, "Penguin Classics", 224, BookStatus.AVAILABLE,
						LocalDateTime.of(2025, 7, 1, 13, 0, 0), null));
	}
}
