package br.dev.hygino.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import br.dev.hygino.notifies.BookReturn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_book_loan")
public final class BookLoan implements BookReturn {

    public BookLoan(User user, Book book) {
        this.book = book;
        this.user = user;
    }

    public BookLoan(@NotNull UUID id, @NotNull User user, @NotNull Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

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

    private boolean active = true;

    @NotNull
    private LocalDate startDate = LocalDate.now();

    @NotNull
    private LocalDate endDate = LocalDate.now().plusDays(10);

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

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