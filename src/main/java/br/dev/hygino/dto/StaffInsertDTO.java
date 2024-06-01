package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;

public record StaffInsertDTO(
       @NotBlank String name,
       @NotBlank String position,
       @NotBlank String phone,
       @NotBlank  String email) {          
}
