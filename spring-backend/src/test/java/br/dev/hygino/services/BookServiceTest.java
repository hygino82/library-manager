package br.dev.hygino.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.dev.hygino.BookFactory;
import br.dev.hygino.dto.BookReportDto;
import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.mappers.BookMapper;
import br.dev.hygino.models.Book;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.services.exceptions.BookNotFoundException;
import br.dev.hygino.services.exceptions.BorrowBookException;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

	private Book newBookEntity;
	private RequestBookDto bookInsert;
	private UUID nonExistingId, bookIdWithoutLoan, dependentId;

	@InjectMocks
	private BookService bookService;

	private BookMapper bookMapper;

	@Mock
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		dependentId = UUID.fromString("eda7e654-e28a-4b56-a2f0-e87c6b3d4c07");
		nonExistingId = UUID.fromString("529585d4-8948-4b26-aca5-94d8f9f8d83b");
		bookIdWithoutLoan = UUID.fromString("91173563-8b2b-42d9-b934-d3604ff6f68a");

		newBookEntity = BookFactory.createBookEntityWithoutUse();
		bookInsert = BookFactory.createNewBookRequest();

		when(bookRepository.save(ArgumentMatchers.any())).thenReturn(newBookEntity);

		when(bookRepository.findById(bookIdWithoutLoan)).thenReturn(Optional.of(newBookEntity));
		when(bookRepository.findById(nonExistingId)).thenReturn(Optional.empty());

		when(bookRepository.findAll()).thenReturn(BookFactory.createBookList());

		doThrow(DataIntegrityViolationException.class).when(bookRepository).deleteById(dependentId);

		bookMapper = Mappers.getMapper(BookMapper.class);
		bookService = new BookService(bookRepository, bookMapper);
	}

	@Test
	@DisplayName("Deve lançar BorrowBookException quando remover um Livro que está emprestado")
	public void removeShouldThrowBorrowBookExceptionWhenBookInUse() {
		final var res = assertThrows(BorrowBookException.class, () -> {
			bookService.remove(dependentId);
		});

		assertEquals("Não pode excluir um livro com empréstimo", res.getMessage());
	}

	@Test
	@DisplayName("Deve retornar um livro quando os dados forem válidos")
	void insertShouldReturnBookEntityWhenDataIsValid() {
		final ResponseBookDto res = bookService.insert(bookInsert);
		assertNotNull(res);
		// estou testando Id porém uuid gerado não é indepotente
		assertEquals(bookIdWithoutLoan, res.id());

		assertEquals("O Senhor dos anéis", res.title());
		assertEquals(1, res.edition());
		assertEquals("J. R. R. Tolkien", res.author());
		assertEquals("Sextante", res.publisher());
		assertEquals("eng01", res.personalCode());
		assertEquals(1212, res.totalPages());
	}

	@Test
	@DisplayName("Deve lançar BookNotFoundException quando o Id for inválido")
	void findByIdShouldThrowBookNotFoundExceptionWhenIdDoesNotExists() {
		final var res = assertThrows(BookNotFoundException.class,
				() -> bookService.findById(nonExistingId));
		assertEquals("Não existe livro com o id: " + nonExistingId, res.getMessage());
	}

	@Test
	@DisplayName("Deve retornar um livro quando o Id for válido")
	void findByIdShouldReturnBookWhenIdIsValid() {
		final ResponseBookDetailsDto res = bookService.findById(bookIdWithoutLoan);
		assertNotNull(res);
		assertEquals(bookIdWithoutLoan, res.id());
		assertEquals("O Senhor dos anéis", res.title());
		assertEquals(1, res.edition());
		assertEquals("J. R. R. Tolkien", res.author());
		assertEquals("Sextante", res.publisher());
		assertEquals("eng01", res.personalCode());
		assertEquals(1212, res.totalPages());
	}

	@Test
	@DisplayName("Deve mostrar o relatório dos livros")
	void ShouldReturnBookReport() {
		final BookReportDto result = bookService.getBookReport();
		final long totalBooks = 5L, borrowedBooks = 2L, availableBooks = 3L;

		assertEquals(totalBooks, result.totalBooks());
		assertEquals(borrowedBooks, result.borrowedBooks());
		assertEquals(availableBooks, result.availableBooks());
		assertEquals(result.totalBooks(), result.borrowedBooks() + result.availableBooks());
		assertNotNull(result.searchDate());
	}
}
