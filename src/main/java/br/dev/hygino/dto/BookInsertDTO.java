package br.dev.hygino.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookInsertDTO(

        @NotBlank String title,

        @NotBlank String author,

        @NotBlank String publisher,

        @NotNull Integer year,

        @NotNull Integer amount,

        @NotBlank String location,

        @NotBlank String isbn,

        @NotBlank String gender) {

}
