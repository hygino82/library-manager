package br.dev.hygino.dto;

import br.dev.hygino.models.SchoolAtribute;

import java.util.UUID;

public record ResponseMinUserDto(
        UUID id,
        String name,
        SchoolAtribute schoolAtribute,
        boolean hasLoan
) {
}
