package br.dev.hygino.dto;

import java.time.LocalDate;

import br.dev.hygino.models.BookLoan;

public record ResponseBookLoanDto(
        Long id,
        Long bookId,
        Long userId,
        String bookTitle,
        String userName,
        LocalDate startDate,
        LocalDate endDate,
        String status) {
    public ResponseBookLoanDto(BookLoan obj) {
        this(
                obj.getId(),
                obj.getBook().getId(),
                obj.getUser().getId(),
                obj.getBook().getTitle(),
                obj.getUser().getName(),
                obj.getStartDate(),
                obj.getEndDate(),
                obj.getBook().getBookStatus().name());
    }
}
