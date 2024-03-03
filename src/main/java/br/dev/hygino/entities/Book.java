package br.dev.hygino.entities;

import java.io.Serializable;

import br.dev.hygino.dto.InsertBookDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Size(max = 100)
	private String title;

	@Size(max = 100)
	private String publisher;

	@Size(max = 100)
	private String isbn;

	private Short releaseYear;
	private Integer leftAmount;
	private Integer totalAmount;

	public Book(InsertBookDTO dto) {
		title = dto.title();
		isbn = dto.isbn();
		releaseYear = dto.year();
		leftAmount = dto.leftAmount();
		totalAmount = dto.totalAmount();
		publisher = dto.publisher();
	}
}
