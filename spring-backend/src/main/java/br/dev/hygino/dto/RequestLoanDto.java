package br.dev.hygino.dto;

import jakarta.validation.constraints.NotNull;

public record RequestLoanDto(
        @NotNull Long userId,
        @NotNull Long bookId) {
}
