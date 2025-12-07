package br.dev.hygino.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.dev.hygino.BookFactory;
import br.dev.hygino.BookLoanFactory;
import br.dev.hygino.UserFactory;
import br.dev.hygino.dto.RequestLoanDto;
import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookLoan;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.BookLoanRepository;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
class BookLoanServiceTest {
	@Mock
	private UserRepository userRepository;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private BookLoanRepository bookLoanRepository;

	@InjectMocks
	private BookLoanService bookLoanService;

	private UUID bookInUseId, bookAvailableId, bookNotExistingId, userAsLoanId, userWithoutLoanId, userNotExistingId;

	@BeforeEach
	void setUp() {
		Book bookEntityAvailable = BookFactory.createBookEntityAvailable();
		Book bookEntityInUse = BookFactory.createBookEntityInUse();
		User userEntityWithoutBookLoan = UserFactory.createUserEntityWithoutBookLoan();
		User userEntityWithBookLoan = UserFactory.createUserEntityWithBookLoan();

		bookInUseId = BookFactory.activeLoanId;
		bookAvailableId = BookFactory.inactiveLoanId;
		bookNotExistingId = UUID.fromString("778b5175-20ee-4fe0-845c-dfc1a63fa53c");

		userAsLoanId = UserFactory.createUserEntityWithBookLoan().getId();
		userWithoutLoanId = UserFactory.createUserEntityWithoutBookLoan().getId();
		userNotExistingId = UUID.fromString("68daa45d-b025-484f-86a3-f45de0f73f82");

		Book returnBook = BookFactory.createBookEntityInUse();

		// when(bookRepository.save(bookEntityAvailable)).thenReturn(bookEntityAvailable);
		when(bookRepository.findById(bookInUseId)).thenReturn(Optional.of(bookEntityInUse));
		when(bookRepository.findById(bookAvailableId)).thenReturn(Optional.of(bookEntityAvailable));
		when(bookRepository.findById(bookNotExistingId)).thenReturn(Optional.empty());

		when(userRepository.findById(userAsLoanId)).thenReturn(Optional.of(userEntityWithBookLoan));
		when(userRepository.findById(userWithoutLoanId)).thenReturn(Optional.of(userEntityWithoutBookLoan));
		when(userRepository.findById(userNotExistingId)).thenReturn(Optional.empty());

		when(bookLoanRepository.save(any())).thenReturn(new BookLoan(userEntityWithBookLoan, bookEntityAvailable));
		when(bookLoanRepository.findAll(PageRequest.of(0, 2))).thenReturn(BookLoanFactory.createBookLoanPage());
		when(bookLoanRepository.findLoanByBook(bookEntityInUse))
				.thenReturn(Optional.of(BookLoanFactory.createBookLoanActive()));

		returnBook.setBookStatus(BookStatus.AVAILABLE);

		when(bookLoanRepository.findLoanByBook(bookEntityInUse))
				.thenReturn(Optional.of(BookLoanFactory.createBookLoanActive()));

		bookLoanService = new BookLoanService(bookLoanRepository, userRepository, bookRepository);
	}

	@Test
	@DisplayName("Deve retornar um empréstimo quando o usuário não tiver livros emprestados e o livro estiver disponível")
	void whenUserHasNoLoanAndTheBookIsAvailableReturnLoan() {
		ResponseBookLoanDto res = bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookAvailableId));
		Assertions.assertNotNull(res);

		Assertions.assertEquals(BookFactory.activeLoanId, res.bookId());
		Assertions.assertEquals(UserFactory.userEntityWithoutLoanId, res.userId());
		Assertions.assertTrue(res.userHasLoan());
		Assertions.assertEquals(BookStatus.IN_USE, res.bookStatus());
	}

	@Test
	@DisplayName("Deve lançar IllegalArgumentException quando o id do usuário não existir")
	void shouldThrowExceptionWhenInvalidUserId() throws RuntimeException {
		IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.insert(new RequestLoanDto(userNotExistingId, bookAvailableId)));
		Assertions.assertEquals("Usuário não encontrado!", res.getMessage());
	}

	@Test
	@DisplayName("Deve lançar IllegalArgumentException quando o id do livro não existir")
	void shouldThrowExceptionWhenInvalidBookId() throws RuntimeException {
		IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookNotExistingId)));
		Assertions.assertEquals("Livro não encontrado!", res.getMessage());
	}

	@Test
	@DisplayName("Deve lançar IllegalArgumentException quando o usuário tiver livro emprestado")
	void shouldThrowExceptionWhenUserHasLoan() throws RuntimeException {
		IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.insert(new RequestLoanDto(userAsLoanId, bookAvailableId)));
		Assertions.assertEquals("O usuário já possui um empréstimo ativo!", res.getMessage());
	}

	@Test
	@DisplayName("Deve lançar IllegalArgumentException quando o livro estiver emprestado")
	void shouldThrowExceptionWhenBookInUse() throws RuntimeException {
		IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookInUseId)));
		Assertions.assertEquals("O livro não está disponível para empréstimo!", res.getMessage());
	}

	@Test
	@DisplayName("O método findAll deve retornar uma página")
	void findAllShouldReturnPage() {
		final PageRequest pageable = PageRequest.of(0, 2);
		final Page<ResponseBookLoanDto> res = bookLoanService.findAllLoans(pageable);
		Assertions.assertNotNull(res);
		Assertions.assertEquals(2, res.getContent().size());
		Assertions.assertEquals("O Guarani", res.getContent().get(0).bookTitle());
		Assertions.assertEquals("Iracema", res.getContent().get(1).bookTitle());
		Assertions.assertEquals("Juvenal Mendes", res.getContent().get(0).userName());
		Assertions.assertEquals("Gorete Medeiros", res.getContent().get(1).userName());
	}

	@Test
	@DisplayName("Deve lançar exceção quando Id do livro for inválido")
	void returnBookShouldThrowExceptionWhenInvalidBookId() {
		final var res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.returnBook(bookNotExistingId));
		Assertions.assertEquals("Livro não encontrado!", res.getMessage());
	}

	@Test
	@DisplayName("Ao devolver um livro, deve desmarcar o empréstimo e deixar o usuário sem empréstimo ativo")
	void returnBookShouldUpdateStatusAndClearUserLoan() {

		// --- ARRANGE ---

		// Book carregado pelo findById
		Book book = BookFactory.createBookEntityInUse();
		when(bookRepository.findById(bookInUseId)).thenReturn(Optional.of(book));

		// User do empréstimo
		User user = UserFactory.createUserEntityWithBookLoan();

		// Empréstimo ativo retornado pelo findLoanByBook
		BookLoan activeLoan = BookLoanFactory.createBookLoanActive();
		activeLoan.setBook(book);
		activeLoan.setUser(user);

		when(bookLoanRepository.findLoanByBook(book)).thenReturn(Optional.of(activeLoan));

		// Mock dos saves (o service chama save de TUDO)
		when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));
		when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

		// Empréstimo inativo retornado pelo save final
		BookLoan inactiveLoan = BookLoanFactory.createBookLoanInactive();
		inactiveLoan.setBook(book);
		inactiveLoan.setUser(user);

		when(bookLoanRepository.save(any(BookLoan.class))).thenReturn(inactiveLoan);

		// --- ACT ---
		ResponseBookLoanDto res = bookLoanService.returnBook(bookInUseId);

		// --- ASSERT ---
		Assertions.assertNotNull(res);
		Assertions.assertEquals("Iracema", res.bookTitle());
		Assertions.assertEquals(BookStatus.AVAILABLE, res.bookStatus());
		Assertions.assertFalse(res.userHasLoan());
		Assertions.assertFalse(res.hasActiveLoan());
	}

	@Test
	@DisplayName("Deve lançar exceção quando o livro não for cadastrado")
	void renewBookShouldThrowExceptionWhenInvalidId() {
		final var res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.renewBook(bookNotExistingId));
		Assertions.assertEquals("Livro não encontrado!", res.getMessage());
	}

	@Test
	@DisplayName("Deve lançar exceção do livro não tiver empréstimo")
	void returnBookShouldThrowExceptionWhenBookDoesNotHaveLoan() {
		// final long bookIdWithoutLoan = 4L;
		when(bookRepository.findById(bookAvailableId))
				.thenReturn(Optional.of(BookFactory.createBookEntityAvailable()));
		/*
		 * when(bookLoanRepository.findLoanByBook(BookFactory.
		 * createBookEntityWithoutLoans())) .thenReturn(Optional.empty());
		 */

		final var res = Assertions.assertThrows(IllegalArgumentException.class,
				() -> bookLoanService.returnBook(bookAvailableId));
		Assertions.assertEquals("Livro não está emprestado!", res.getMessage());
	}
}