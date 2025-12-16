package br.dev.hygino.services;

import br.dev.hygino.BookFactory;
import br.dev.hygino.dto.BookReportDto;
import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.services.exceptions.BookNotFoundException;
import br.dev.hygino.services.exceptions.BorrowBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookServiceTestIT {

    private RequestBookDto bookInsert;
    private UUID invalidId, bookIdWithoutLoan, dependentId;

    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setup() {
        bookIdWithoutLoan = UUID.fromString("b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50111");
        dependentId = UUID.fromString("b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105");
        invalidId = UUID.fromString("7760bd63-04e3-46a5-a159-9d328ce1f7a5");
        bookInsert = BookFactory.createNewBookRequest();
    }

    @Test
    @DisplayName("Deve retornar um livro quando o Id for válido")
    public void findByIdShouldReturnBookWhenIdIsValid() {
        final ResponseBookDetailsDto res = bookService.findById(bookIdWithoutLoan);
        assertNotNull(res);
        assertEquals(bookIdWithoutLoan, res.id());
        assertEquals("Quincas Borba", res.title());
        assertEquals(1, res.edition());
        assertEquals("Machado de Assis", res.author());
        assertEquals("Editora Record", res.publisher());
        assertEquals("BR100125", res.personalCode());
        assertEquals(247, res.totalPages());
    }

    @Test
    @DisplayName("Deve retornar um livro quando os dados forem válidos")
    public void insertShouldReturnBookEntityWhenDataIsValid() {
        final ResponseBookDto res = bookService.insert(bookInsert);
        assertNotNull(res.id());
        assertEquals("O Senhor dos anéis", res.title());
        assertEquals(1, res.edition());
        assertEquals("J. R. R. Tolkien", res.author());
        assertEquals("Sextante", res.publisher());
        assertEquals("eng01", res.personalCode());
        assertEquals(1212, res.totalPages());
    }

    @Test
    @DisplayName("Deve lançar Exception quando o Id do livro for inválido")
    public void removeShouldThrowBorrowBookExceptionWhenInvalidBookId() {
        final BorrowBookException res = assertThrows(BorrowBookException.class, () -> bookService.remove(dependentId));

        assertEquals("Não pode excluir um livro com empréstimo", res.getMessage());
    }


    @Test
    @DisplayName("Deve lançar BookNotFoundException quando o Id for inválido")
    void findByIdShouldThrowIllegalArgumentExceptionWhenIdDoesNotExists() {
        final BookNotFoundException res = assertThrows(BookNotFoundException.class,
                () -> bookService.findById(invalidId));

        assertEquals("Não existe livro com o id: " + invalidId, res.getMessage());
    }


    @Test
    @DisplayName("Deve mostrar o relatório dos livros")
    public void shouldReturnBookReport() {
        final BookReportDto result =
                bookService.getBookReport();
        final long totalBooks = 11L, borrowedBooks = 2L,
                availableBooks = 9L;

        assertEquals(totalBooks, result.totalBooks());
        assertEquals(borrowedBooks, result.borrowedBooks());
        assertEquals(availableBooks, result.availableBooks());
        assertEquals(result.totalBooks(), result.borrowedBooks() + result.availableBooks());
        assertNotNull(result.searchDate());
    }
}
