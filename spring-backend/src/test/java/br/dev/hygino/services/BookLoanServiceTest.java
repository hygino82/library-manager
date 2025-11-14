package br.dev.hygino.services;

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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    private Book bookEntityAvailable;
    private Book bookEntityInUse;
    private User userEntityWithBookLoan;
    private User userEntityWithoutBookLoan;
    private long bookInUseId, bookAvailableId, bookNotExistingId, userAsLoanId, userWithoutLoanId, userNotExistingId;

    @BeforeEach
    void setUp() {
        bookEntityAvailable = BookFactory.createBookEntityAvailable();
        bookEntityInUse = BookFactory.createBookEntityInUse();
        userEntityWithoutBookLoan = UserFactory.createUserEntityWithoutBookLoan();
        userEntityWithBookLoan = UserFactory.createUserEntityWithBookLoan();

        bookInUseId = 2L;
        bookAvailableId = 1L;
        bookNotExistingId = 1000L;

        userAsLoanId = 2L;
        userWithoutLoanId = 1L;
        userNotExistingId = 1000L;


        when(bookRepository.findById(bookInUseId)).thenReturn(Optional.of(bookEntityInUse));
        when(bookRepository.findById(bookAvailableId)).thenReturn(Optional.of(bookEntityAvailable));
        when(bookRepository.findById(bookNotExistingId)).thenReturn(Optional.empty());

        when(userRepository.findById(userAsLoanId)).thenReturn(Optional.of(userEntityWithBookLoan));
        when(userRepository.findById(userWithoutLoanId)).thenReturn(Optional.of(userEntityWithoutBookLoan));
        when(userRepository.findById(userNotExistingId)).thenReturn(Optional.empty());

        when(bookLoanRepository.save(any())).thenReturn(new BookLoan(userEntityWithoutBookLoan, bookEntityAvailable));
        when(bookLoanRepository.findAll(PageRequest.of(0, 2))).thenReturn(BookLoanFactory.createBookLoanPage());

        bookLoanService = new BookLoanService(bookLoanRepository, userRepository, bookRepository);
    }

    @Test
    @DisplayName("Deve retornar um empréstimo quando o usuário não tiver livros emprestados e o livro estiver disponível")
    void whenUserHasNoLoanAndTheBookIsAvailableReturnLoan() {
        ResponseBookLoanDto res = bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookAvailableId));
        Assertions.assertNotNull(res);

        Assertions.assertEquals(bookAvailableId, res.bookId());
        Assertions.assertEquals(userWithoutLoanId, res.userId());
        Assertions.assertTrue(res.userHasLoan());
        Assertions.assertEquals(BookStatus.IN_USE, res.bookStatus());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o id do usuário não existir")
    void shouldThrowExceptionWhenInvalidUserId() throws RuntimeException {
        IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class, () -> bookLoanService.insert(new RequestLoanDto(userNotExistingId, bookAvailableId)));
        Assertions.assertEquals("Usuário não encontrado!", res.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o id do livro não existir")
    void shouldThrowExceptionWhenInvalidBookId() throws RuntimeException {
        IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class, () -> bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookNotExistingId)));
        Assertions.assertEquals("Livro não encontrado!", res.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o usuário tiver livro emprestado")
    void shouldThrowExceptionWhenUserHasLoan() throws RuntimeException {
        IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class, () -> bookLoanService.insert(new RequestLoanDto(userAsLoanId, bookAvailableId)));
        Assertions.assertEquals("O usuário já possui um empréstimo ativo!", res.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando o livro estiver emprestado")
    void shouldThrowExceptionWhenBookInUse() throws RuntimeException {
        IllegalArgumentException res = Assertions.assertThrows(IllegalArgumentException.class, () -> bookLoanService.insert(new RequestLoanDto(userWithoutLoanId, bookInUseId)));
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
}