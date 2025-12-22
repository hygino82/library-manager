package br.dev.hygino.dto;

import br.dev.hygino.models.BookLoan;

import java.time.LocalDate;
import java.util.UUID;

public record BookLoanReportDto(
        UUID id,
        String bookTitle,
        String userName,
        LocalDate startDate,
        LocalDate endDate,
        boolean hasActiveLoan,
        boolean delayed
) {

    public BookLoanReportDto(BookLoan obj) {
        this(
                obj.getId(),
                obj.getBook().getTitle(),
                obj.getUser().getName(),
                obj.getStartDate(),
                obj.getEndDate(),
                obj.isActive(),
                LocalDate.now().isAfter(obj.getEndDate())
        );
    }
}
