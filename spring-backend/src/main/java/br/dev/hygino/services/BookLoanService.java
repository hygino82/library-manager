package br.dev.hygino.services;

import java.util.List;
import java.util.UUID;

import br.dev.hygino.notifies.BookReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.dev.hygino.dto.RequestLoanDto;
import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookLoan;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.BookLoanRepository;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.repositories.UserRepository;
import br.dev.hygino.services.exceptions.BookLoanNotFoundException;

@Service
public class BookLoanService {

	private final BookLoanRepository bookLoanRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;

	public BookLoanService(BookLoanRepository bookLoanRepository, UserRepository userRepository,
			BookRepository bookRepository) {
		this.bookLoanRepository = bookLoanRepository;
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
	}

	@Transactional(readOnly = true)
	public Page<ResponseBookLoanDto> findAllLoans(Pageable pageable) {
		return bookLoanRepository.findAll(pageable).map(ResponseBookLoanDto::new);
	}

	@Transactional
	public ResponseBookLoanDto insert(RequestLoanDto dto) {
		User user = userRepository.findById(dto.userId())
				.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

		if (user.isHasLoan()) {
			throw new IllegalArgumentException("O usuário já possui um empréstimo ativo!");
		}

		Book book = bookRepository.findById(dto.bookId())
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));

		if (book.getBookStatus() != BookStatus.AVAILABLE) {
			throw new IllegalArgumentException("O livro não está disponível para empréstimo!");
		}

		user.setHasLoan(true);
		book.setBookStatus(BookStatus.IN_USE);

		BookLoan bookLoan = new BookLoan(user, book);
		bookLoanRepository.save(bookLoan);

		return new ResponseBookLoanDto(bookLoan);
	}

	@Transactional
	public ResponseBookLoanDto returnBook(UUID bookId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));

		if (book.getBookStatus() == BookStatus.AVAILABLE) {
			throw new IllegalArgumentException("Livro não está emprestado!");
		}

		BookLoan bookLoan = bookLoanRepository.findLoanByBook(book)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum empréstimo encontrado para o livro!"));

		User user = bookLoan.getUser();

		List<BookReturn> returnNotifications = List.of(bookLoan, bookLoan.getBook(), bookLoan.getUser());

		// executa as notificações de retorno do livro
		returnNotifications.forEach(BookReturn::executeReturn);
		// bookLoan.setActive(false);
		// Salva mudanças explicitamente
		bookRepository.save(book);
		userRepository.save(user);
		bookLoan = bookLoanRepository.save(bookLoan);

		return new ResponseBookLoanDto(bookLoan);
	}

	@Transactional
	public ResponseBookLoanDto renewBook(UUID bookId) {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));

		BookLoan bookLoan = bookLoanRepository.findLoanByBook(book)
				.orElseThrow(() -> new IllegalArgumentException("Nenhum empréstimo ativo encontrado para o livro!"));

		if (!bookLoan.isActive()) {
			throw new IllegalArgumentException("O livro já foi devolvido!");
		}

		bookLoan.setEndDate(bookLoan.getEndDate().plusDays(5));

		bookLoanRepository.save(bookLoan);

		return new ResponseBookLoanDto(bookLoan);
	}

	@Transactional(readOnly = true)
	public ResponseBookLoanDto findLoanById(UUID id) {
		final Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookLoanNotFoundException("Livro não encontrado!"));
		return new ResponseBookLoanDto(null);
	}
}
