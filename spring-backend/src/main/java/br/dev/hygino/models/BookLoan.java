package br.dev.hygino.models;

import br.dev.hygino.notifies.BookReturn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_book_loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class BookLoan implements BookReturn {

    public BookLoan(User user, Book book) {
        this.book = book;
        this.user = user;
    }

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    //@Setter(AccessLevel.PRIVATE)
    private boolean active = true;

    @NotNull
    private LocalDate startDate = LocalDate.now();

    @NotNull
    private LocalDate endDate = LocalDate.now().plusDays(10);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        BookLoan bookLoan = (BookLoan) o;
        return Objects.equals(id, bookLoan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public void executeReturn() {
        active = false;
        endDate = LocalDate.now();
    }
}
