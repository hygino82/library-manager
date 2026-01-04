package br.dev.hygino.services;

import br.dev.hygino.dto.BookLoanReportDto;
import br.dev.hygino.dto.RequestLoanDto;
import br.dev.hygino.dto.RequestLoanWithEmailAndCodeDto;
import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.services.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookLoanServiceTestIT {

    @Autowired
    private BookLoanService bookLoanService;

    private String emailWithoutLoan, invalidEmail, validPersonalCode, invalidPersonalCode, emailWithLoan, bookCodeWithLoan;
    private UUID userWithoutLoanId, invalidUserid, invalidBookId, bookWithoutLoanId, userWithLoanId, bookWithLoanId, validLoanId, invalidLoanId;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        emailWithoutLoan = "rafael.lima@email.com";
        invalidEmail = "invalid.user@email.com";
        validPersonalCode = "BR100125";
        emailWithLoan = "juvenal@email.com";
        bookCodeWithLoan = "BR1003";
        invalidPersonalCode = "INVALID_CODE001";
        userWithoutLoanId = UUID.fromString("c7b2c61a-ff37-4a76-94ef-9c4d0b701005");
        invalidUserid = UUID.fromString("7d1e12d8-1905-47bc-b261-296bb0b96bb5");
        bookWithoutLoanId = UUID.fromString("b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50111");
        validLoanId = UUID.fromString("d4e39276-9a98-4edf-9fbb-20b2ec700001");
        userWithLoanId = UUID.fromString("c7b2c61a-ff37-4a76-94ef-9c4d0b701001");
        bookWithLoanId = UUID.fromString("b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105");
        invalidBookId = invalidLoanId = invalidUserid;
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Deve retornar uma página com dois empréstimos de livros")
    public void findAllLoansShouldReturnPageWithTwoElements() {
        final Page<BookLoanReportDto> result = bookLoanService.findAllLoans(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Vidas Secas", result.getContent().getFirst().bookTitle());
        assertEquals("Juvenal Santos", result.getContent().getFirst().userName());
        assertEquals("Memórias de um Sargento de Milícias", result.getContent().get(1).bookTitle());
        assertEquals("Maria Oliveira", result.getContent().get(1).userName());
    }

    @Test
    @DisplayName("Deve criar um empréstimo de livro quando email e o código do livro forem válidos")
    public void insertUsingEmailAndCodeShouldReturnLoanWhenValidUserIdAndBookId() {
        final RequestLoanWithEmailAndCodeDto request = new RequestLoanWithEmailAndCodeDto(emailWithoutLoan, validPersonalCode);
        final ResponseBookLoanDto result = bookLoanService.insertUsingEmailAndCode(request);

        assertNotNull(result);
        assertEquals("Quincas Borba", result.bookTitle());
        assertEquals("Rafael Lima", result.userName());
        assertTrue(result.hasActiveLoan(), "O livro possui empréstimo ativo!");
        assertTrue(result.userHasLoan(), "O usuário já tem livro emprestado!");
    }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando inserir um empréstimo com email inválido")
    public void insertUsingEmailAndCodeShouldThrowUserNotFoundExceptionWhenInvalidEmail() {
        final RequestLoanWithEmailAndCodeDto request = new RequestLoanWithEmailAndCodeDto(invalidEmail, validPersonalCode);

        final var result = assertThrows(UserNotFoundException.class,
                () -> bookLoanService.insertUsingEmailAndCode(request));

        assertEquals("Usuário não encontrado!", result.getMessage());
    }

    @Test
    @DisplayName("Deve lançar UserAlreadyBorrowedBookException quando inserir um empréstimo quanto usuário tiver empréstimo ativo")
    public void insertUsingEmailAndCodeShouldThrowUserAlreadyBorrowedBookExceptionWhenUserHasLoan() {
        final RequestLoanWithEmailAndCodeDto request = new RequestLoanWithEmailAndCodeDto(emailWithLoan, validPersonalCode);

        final var result = assertThrows(UserAlreadyBorrowedBookException.class,
                () -> bookLoanService.insertUsingEmailAndCode(request));

        assertEquals("O usuário já possui um empréstimo ativo!", result.getMessage());
    }

    @Test
    @DisplayName("Deve lançar BookNotFoundException quando inserir um empréstimo com o código do livro for inválido")
    public void insertUsingEmailAndCodeShouldThrowBookNotFoundExceptionWhenInvalidId() {
        final RequestLoanWithEmailAndCodeDto request = new RequestLoanWithEmailAndCodeDto(emailWithoutLoan, invalidPersonalCode);

        final var result = assertThrows(BookNotFoundException.class,
                () -> bookLoanService.insertUsingEmailAndCode(request));

        assertEquals("Livro não encontrado!", result.getMessage());
    }

    @Test
    @DisplayName("Deve lançar BookAlreadyLoanedException quando inserir um empréstimo para um livro que está emprestado")
    public void insertUsingEmailAndCodeShouldThrowBookAlreadyLoanedExceptionWhenBookAsLoan() {
        final RequestLoanWithEmailAndCodeDto request = new RequestLoanWithEmailAndCodeDto(emailWithoutLoan, bookCodeWithLoan);

        final var result = assertThrows(BookAlreadyLoanedException.class,
                () -> bookLoanService.insertUsingEmailAndCode(request));

        assertEquals("O livro não está disponível para empréstimo!", result.getMessage());
    }

    @Test
    @DisplayName("Deve criar um empréstimo de livro quando os ids do usuário e do livro forem válidos")
    public void insertUsingIdsUsingIdsShouldReturnLoanWhenValidUserIdAndBookId() {
        final RequestLoanDto request = new RequestLoanDto(userWithoutLoanId, bookWithoutLoanId);
        final ResponseBookLoanDto result = bookLoanService.insertUsingIds(request);

        assertNotNull(result);
        assertEquals("Quincas Borba", result.bookTitle());
        assertEquals("Rafael Lima", result.userName());
        assertTrue(result.hasActiveLoan(), "O livro possui empréstimo ativo!");
        assertTrue(result.userHasLoan(), "O usuário já tem livro emprestado!");
    }

    @Test
    @DisplayName("Deve lançar UserNotFoundException quando userId for inválido")
    public void insertUsingIdsShouldThrowUserNotFoundExceptionWhenInvalidUserId() {
        final RequestLoanDto request = new RequestLoanDto(invalidUserid, bookWithoutLoanId);

        final var result = assertThrows(UserNotFoundException.class,
                () -> bookLoanService.insertUsingIds(request));

        assertEquals("Usuário não encontrado!", result.getMessage());
    }

    @Test
    @DisplayName("Deve lançar BookNotFoundException quando userId for inválido")
    public void insertUsingIdsShouldThrowBookNotFoundExceptionWhenInvalidBookId() {
        final RequestLoanDto request = new RequestLoanDto(userWithoutLoanId, invalidBookId);

        final var result = assertThrows(BookNotFoundException.class,
                () -> bookLoanService.insertUsingIds(request));

        assertEquals("Livro não encontrado!", result.getMessage());
    }


    @Test
    @DisplayName("Deve lançar UserAlreadyBorrowedBookException quando inserir um empréstimo quanto usuário tiver empréstimo ativo")
    public void insertUsingIdsShouldThrowUserAlreadyBorrowedBookExceptionWhenUserHasLoan() {
        final RequestLoanDto request = new RequestLoanDto(userWithLoanId, bookWithoutLoanId);

        final var result = assertThrows(UserAlreadyBorrowedBookException.class,
                () -> bookLoanService.insertUsingIds(request));

        assertEquals("O usuário já possui um empréstimo ativo!", result.getMessage());
    }

    @Test
    @DisplayName("Deve lançar BookAlreadyLoanedException quando inserir um empréstimo para um livro que está emprestado")
    public void insertUsingIdsShouldThrowBookAlreadyLoanedExceptionWhenBookAsLoan() {
        final RequestLoanDto request = new RequestLoanDto(userWithoutLoanId, bookWithLoanId);

        final var result = assertThrows(BookAlreadyLoanedException.class,
                () -> bookLoanService.insertUsingIds(request));

        assertEquals("O livro não está disponível para empréstimo!", result.getMessage());
    }


    @Test
    @DisplayName("FindLoanById deve retornar o um empréstimo quando seu Id for inválido")
    public void findLoanByIdShouldReturnLoanWhenValidLoanId() {
        final var result = bookLoanService.findLoanById(validLoanId);

        assertEquals("Vidas Secas", result.bookTitle());
        assertEquals("Juvenal Santos", result.userName());
    }

    @Test
    @DisplayName("FindLoanById deve lançar BookLoanNotFoundException quando o Id do empréstimo for inválido")
    public void findLoanByIdShouldThrowBookLoanNotFoundExceptionWhenInvalidLoanId() {
        final var result = assertThrows(BookLoanNotFoundException.class,
                () -> bookLoanService.findLoanById(invalidLoanId));

        assertEquals("Livro não encontrado!", result.getMessage());
    }
}
