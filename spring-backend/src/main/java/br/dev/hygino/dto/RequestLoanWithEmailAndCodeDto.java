package br.dev.hygino.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestLoanWithEmailAndCodeDto(
       @NotBlank @Email String email,
       @NotBlank String personalCode) {
}
