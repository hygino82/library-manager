package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}
