package br.dev.hygino.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class User {

    private Long id;
    private String name;
    private String attribute;
    private String contact;
    //private final List<Book> books = new ArrayList<>();
    private boolean acitiveLoan = false;
   // private Book currentBook = null;
}
