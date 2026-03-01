package br.dev.hygino.dto;

public record UpdateBookDto(
        long id,
        String title,
        String author,
        String code,
        int pages,
        String publisher,
        int edition) {
}
