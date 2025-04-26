package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.models.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
}
