package br.dev.hygino.dto;

public record InsertBookDto(
        String title,
        String author,
        String code,
        Integer pages,
        String publisher,
        Integer edition) {
    public InsertBookDto(String title,
                         String author,
                         String code,
                         Integer pages,
                         String publisher) {
        this(title, author, code, pages, publisher, 1);
    }
}
