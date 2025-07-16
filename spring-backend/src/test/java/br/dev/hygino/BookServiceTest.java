package br.dev.hygino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.services.BookService;

@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    private Book bookEntity;
    private RequestBookDto bookInsert;
    private long existingId, nonExistingId;

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;
        bookEntity = BookFactory.createUserEntity();
        bookInsert = BookFactory.createNewBook();

        when(bookRepository.save(ArgumentMatchers.any())).thenReturn(bookEntity);

        when(bookRepository.findById(existingId)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.findById(nonExistingId)).thenReturn(Optional.empty());
    }

    @Test
    @DisplayName("Deve retornar um livro quando os dados forem válidos")
    public void insertShouldReturnBookEntityWhenDataIsValid() {
        final ResponseBookDto res = bookService.insert(bookInsert);
        assertNotNull(res);
        assertEquals(1L, res.id());
        assertEquals("O Senhor dos anéis", res.title());
        assertEquals(1, res.edition());
        assertEquals("J. R. R. Tolkien", res.author());
        assertEquals("Sextante", res.publisher());
        assertEquals("eng01", res.personalCode());
        assertEquals(1212, res.totalPages());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o Id for inválido")
    public void findByIdShouldThrowIllegalArgumentExceptionWhenIdDoesNotExists() {
        final IllegalArgumentException res = assertThrows(IllegalArgumentException.class, () -> bookService.findById(nonExistingId));
        assertEquals("Não existe livro com o id: " + nonExistingId, res.getMessage());
    }

    @Test
    @DisplayName("Deve retornar um livro quando o Id for válido")
    public void findByIdShouldReturnBookWhenIdIsValid() {
        final ResponseBookDto res = bookService.findById(existingId);
        assertNotNull(res);
        assertEquals(1L, res.id());
        assertEquals("O Senhor dos anéis", res.title());
        assertEquals(1, res.edition());
        assertEquals("J. R. R. Tolkien", res.author());
        assertEquals("Sextante", res.publisher());
        assertEquals("eng01", res.personalCode());
        assertEquals(1212, res.totalPages());
    }
}
