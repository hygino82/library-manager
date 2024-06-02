package br.dev.hygino.dto;

import java.util.Date;

import br.dev.hygino.entities.Loan;

public record LoanDTO(
        Long id,
        Long bookId,
        String bookTitle,
        Long studentId,
        String studentName,
        Date loandate,
        Date returnDate) {

    public LoanDTO(Loan entity) {
        this(
                entity.getId(),
                entity.getBook().getId(),
                entity.getBook().getTitle(),
                entity.getStudent().getId(),
                entity.getStudent().getName(),
                entity.getLoanDate(),
                entity.getReturnDate());
    }
}
