package br.dev.hygino.services;

import java.time.LocalDate;
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
            throw new IllegalArgumentException("O livro com não está disponível para empréstimo!");
        }

        user.setHasLoan(true);
        book.setBookStatus(BookStatus.IN_USE);

        BookLoan bookLoan = new BookLoan(user, book);
        bookLoanRepository.save(bookLoan);

        return new ResponseBookLoanDto(bookLoan);
    }

    @Transactional
    public ResponseBookLoanDto returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + bookId));

        BookLoan bookLoan = bookLoanRepository.findLoanByBook(book)
                .orElseThrow(() -> new IllegalArgumentException("Nenhum empréstimo ativo encontrado para o livro com ID: " + bookId));

        // Atualiza status
        book.setBookStatus(BookStatus.AVAILABLE);
        bookLoan.setEndDate(LocalDate.now());
        User user = bookLoan.getUser();
        user.setHasLoan(false);

        // Salva mudanças explicitamente
        bookRepository.save(book);
        userRepository.save(user);
        bookLoanRepository.save(bookLoan);

        return new ResponseBookLoanDto(bookLoan);
    }

    @Transactional
	public ResponseBookLoanDto renewBook(Long bookId) {
    	 Book book = bookRepository.findById(bookId)
                 .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + bookId));

         BookLoan bookLoan = bookLoanRepository.findLoanByBook(book)
                 .orElseThrow(() -> new IllegalArgumentException("Nenhum empréstimo ativo encontrado para o livro com ID: " + bookId));
         
         bookLoan.setEndDate(bookLoan.getEndDate().plusDays(5));
         
         bookLoanRepository.save(bookLoan);

         return new ResponseBookLoanDto(bookLoan);
	}
}
