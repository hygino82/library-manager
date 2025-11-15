package br.dev.hygino.models;

import br.dev.hygino.notifies.BookReturn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class Book implements BookReturn {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100, min = 3)
    private String title;

    @NotBlank
    @Size(max = 100, min = 3)
    private String author;

    @NotBlank
    @Size(max = 30, min = 3)
    private String personalCode;

    @NotNull
    private Integer edition;

    @NotBlank
    @Size(max = 100, min = 3)
    private String publisher;

    @NotNull
    private Integer totalPages;

    @NotNull
    private BookStatus bookStatus = BookStatus.AVAILABLE;

    @OneToMany(mappedBy = "book")
    private final List<BookLoan> bookLoans = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public void executeReturn() {
        bookStatus = BookStatus.AVAILABLE;
    }
}
