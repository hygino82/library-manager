package br.dev.hygino.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

	@Query("SELECT obj FROM BookLoan obj WHERE obj.book = :book")
	Optional<BookLoan> findLoanByBook(@Param("book") Book book);

}
