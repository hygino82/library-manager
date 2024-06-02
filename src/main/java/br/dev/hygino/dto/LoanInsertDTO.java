package br.dev.hygino.dto;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record LoanInsertDTO(
        @NotNull Long bookId,
        @NotNull Long studentId,
        @Future Date returnDate) {

}
