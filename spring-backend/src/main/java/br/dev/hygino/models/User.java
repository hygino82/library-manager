package br.dev.hygino.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.dev.hygino.notifies.BookReturn;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class User implements BookReturn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100, min = 3)
	private String name;

	@NotNull
	private SchoolAtribute schoolAtribute;

	@Email
	@Size(max = 100, min = 3)
	@NotBlank
	private String email;

	@NotBlank
	@Size(max = 20, min = 8)
	private String phoneNumber;

	@OneToMany(mappedBy = "user")
	private final List<BookLoan> bookLoans = new ArrayList<>();

	private boolean hasLoan = false;

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public void executeReturn() {
		hasLoan = false;
	}
}
