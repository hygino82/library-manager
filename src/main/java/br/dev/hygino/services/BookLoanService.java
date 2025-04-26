package br.dev.hygino.services;

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
        return bookLoanRepository.findAll(pageable)
                .map(ResponseBookLoanDto::new);
    }

    @Transactional
    public ResponseBookLoanDto insert(RequestLoanDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("Não existe usuário com o Id: " + dto.userId()));
                
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Não existe livro com o Id: " + dto.userId()));

        if (book.getBookStatus() != BookStatus.AVALIABLE) {
            new IllegalArgumentException("Não está disponível o livro com o Id: " + dto.bookId());

        }
        book.setBookStatus(BookStatus.UNVALIABLE);

        BookLoan bookLoan = new BookLoan(user, book);
        bookLoan = bookLoanRepository.save(bookLoan);

        return new ResponseBookLoanDto(bookLoan);
    }
}
