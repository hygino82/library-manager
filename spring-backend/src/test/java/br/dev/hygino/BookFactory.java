package br.dev.hygino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;

public final class BookFactory {
	private BookFactory() {
	}

	public static Book createBookEntityAvailable() {
		return new Book(2L, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.AVAILABLE,
				LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static Book createBookEntityInUse() {
		return new Book(2L, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.IN_USE,
				LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static RequestBookDto createNewBook() {
		return new RequestBookDto("O Senhor dos anéis", "J. R. R. Tolkien", "eng01", 1, "Sextante", 1212);
	}

	public static Book createBookEntityWithoutLoans() {
		return new Book(4L, "O Alienista", "Machado de Assis", "br004", 1, "Sextante", 182, BookStatus.AVAILABLE,
				LocalDateTime.of(LocalDate.of(2025, 5, 15), LocalTime.of(14, 12, 3)), null);
	}

	public static List<Book> createBookList() {
		return Arrays.asList(
				new Book(1L, "Iracema", "José de Alencar", "br001", 2, "Principis", 122, BookStatus.AVAILABLE,
						LocalDateTime.of(2025, 5, 15, 14, 12, 3), null),
				new Book(2L, "O Gaúcho", "José de Alencar", "br002", 2, "Principis", 122, BookStatus.IN_USE,
						LocalDateTime.of(2025, 5, 15, 14, 12, 3), null),
				new Book(3L, "Dom Casmurro", "Machado de Assis", "br003", 1, "Companhia das Letras", 256,
						BookStatus.AVAILABLE, LocalDateTime.of(2025, 6, 10, 9, 30, 0), null),
				new Book(4L, "A Moreninha", "Joaquim Manuel de Macedo", "br004", 3, "Editora Melhoramentos", 198,
						BookStatus.IN_USE, LocalDateTime.of(2025, 6, 12, 11, 45, 0), null),
				new Book(5L, "Memórias Póstumas", "Machado de Assis", "br005", 1, "Penguin Classics", 224,
						BookStatus.AVAILABLE, LocalDateTime.of(2025, 7, 1, 13, 0, 0), null));
	}
}
