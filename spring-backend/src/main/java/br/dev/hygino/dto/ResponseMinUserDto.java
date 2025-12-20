package br.dev.hygino.dto;

import br.dev.hygino.models.SchoolAttribute;

import java.util.UUID;

public record ResponseMinUserDto(
        UUID id,
        String name,
        SchoolAttribute schoolAttribute,
        boolean hasLoan
) {
}
