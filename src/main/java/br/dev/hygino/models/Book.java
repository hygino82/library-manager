package br.dev.hygino.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Book {

    private Long id;
    private String title;
    private String author;
    private String code;
    private Integer pages;
    private String publisher;
    private Integer edition = 1;
    private boolean acitiveLoan = false;
}
