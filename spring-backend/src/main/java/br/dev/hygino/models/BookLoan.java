package br.dev.hygino.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_book_loan")
@Getter
@Setter
@NoArgsConstructor
public class BookLoan {

	public BookLoan(User user, Book book) {
		this.book = book;
		this.user = user;
	}

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	private LocalDate startDate = LocalDate.now();
	private LocalDate endDate = LocalDate.now().plusDays(10);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return Objects.equals(id, bookLoan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
