package br.dev.hygino.dto;

public record RequestBookDto(
        String title,
        String author,
        String personalCode,
        Integer edition,
        String publisher,
        Integer totalPages
        ) {
}
