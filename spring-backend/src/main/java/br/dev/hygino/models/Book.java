package br.dev.hygino.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Book {

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
	private BookStatus bookStatus = BookStatus.AVALIABLE;

	@OneToMany(mappedBy = "book")
	private final List<BookLoan> bookLoans = new ArrayList<>();

	private final LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
}
