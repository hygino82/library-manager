package br.dev.hygino.dto;

import br.dev.hygino.models.SchoolAttribute;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUserDto(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String password,
        @NotNull SchoolAttribute schoolAttribute) {

}
