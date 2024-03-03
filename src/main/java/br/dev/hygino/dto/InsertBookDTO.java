package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InsertBookDTO(
        @NotBlank String title,
        @NotBlank String publisher,
        @NotBlank String isbn,
        @NotNull Short year,
        @NotNull Integer leftAmount,
        @NotNull Integer totalAmount) {
}
