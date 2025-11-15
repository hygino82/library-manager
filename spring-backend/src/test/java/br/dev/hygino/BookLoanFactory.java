package br.dev.hygino;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookLoan;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.models.SchoolAtribute;
import br.dev.hygino.models.User;

public class BookLoanFactory {
	private BookLoanFactory() {
	}

	public static Page<BookLoan> createBookLoanPage() {

		User user1 = new User(1L, "Juvenal Mendes", SchoolAtribute.OITAVO, "juvenal@email.com", "4612345678", true);

		User user2 = new User(2L, "Gorete Medeiros", SchoolAtribute.SETIMO, "goretinha@email.com", "4712345678", true);

		Book book1 = new Book(3L, "O Guarani", "José de Alencar", "ptbr01", 2, "Arqueiro", 245, BookStatus.IN_USE,
				LocalDateTime.of(2025, 6, 17, 12, 25, 40), null);

		Book book2 = new Book(2L, "Iracema", "José de Alencar", "ptbr02", 2, "Sextante", 198, BookStatus.IN_USE,
				LocalDateTime.of(2025, 6, 18, 12, 25, 40), null);

		BookLoan bookLoan1 = new BookLoan(user1, book1);
		BookLoan bookLoan2 = new BookLoan(user2, book2);

		// cria uma lista para retornar no Page
		List<BookLoan> list = List.of(bookLoan1, bookLoan2);

		// cria e retorna o Page usando PageImpl
		return new PageImpl<>(list);
	}

	public static BookLoan createBookLoanActive() {
		return new BookLoan(
				1L, 
				UserFactory.createUserEntityWithBookLoan(),
				BookFactory.createBookEntityInUse(),
				true,
				LocalDate.of(2025, 11, 15),
				LocalDate.of(2025, 11, 25));
	}

    public static BookLoan createBookLoanInactive(){
        return new BookLoan(
                5L,
                UserFactory.createUserEntityWithoutBookLoan(),
                BookFactory.createBookEntityAvailable(),
                false,
                LocalDate.of(2025, 11, 15),
                LocalDate.of(2025, 11, 25));
    }
}
