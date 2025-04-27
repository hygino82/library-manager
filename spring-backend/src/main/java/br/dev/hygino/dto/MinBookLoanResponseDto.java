package br.dev.hygino.dto;

import java.time.LocalDate;

public record MinBookLoanResponseDto(
        Long id,
        String title,
        LocalDate startAt
) {
}
