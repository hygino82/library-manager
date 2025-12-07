package br.dev.hygino.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MinBookLoanResponseDto(
        UUID id,
        String title,
        LocalDate startAt
) {
}
