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
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book implements BookReturn {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@NotBlank
	@Size(max = 100, min = 3)
	private String title;

	@NotBlank
	@Size(max = 100, min = 3)
	private String author;

	@NotBlank
	@Column(unique = true)
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
	@Enumerated(EnumType.STRING)
	private BookStatus bookStatus = BookStatus.AVAILABLE;

	@OneToMany(mappedBy = "book")
	private final List<BookLoan> bookLoans = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
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

	public Book(String title, String author, String personalCode, Integer edition, String publisher, Integer totalPages, BookStatus bookStatus, Category category) {
		this.title = title;
		this.author = author;
		this.personalCode = personalCode;
		this.edition = edition;
		this.publisher = publisher;
		this.totalPages = totalPages;
		this.bookStatus = bookStatus;
		this.category = category;
	}
}
