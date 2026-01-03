package br.dev.hygino.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.dev.hygino.projections.LoanDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookLoan;

public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {

    @Query("SELECT obj FROM BookLoan obj WHERE obj.book = :book")
    Optional<BookLoan> findLoanByBook(@Param("book") Book book);

    @Query(value = """
    SELECT
        u.name  AS name,
        b.title AS title,
        LOWER(HEX(l.id)) AS id,
        l.active as active
    FROM tb_user u
    JOIN tb_book_loan l ON u.id = l.user_id
    JOIN tb_book b ON b.id = l.book_id
    """, nativeQuery = true)
    List<LoanDetailsProjection> findBooksWithLoans();

}
