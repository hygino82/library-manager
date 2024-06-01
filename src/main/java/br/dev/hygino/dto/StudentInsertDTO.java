package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentInsertDTO(
        @NotBlank String name,
        @NotBlank String address,
        String phone,
        String email,
        @NotBlank String className) {
}
