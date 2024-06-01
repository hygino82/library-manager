package br.dev.hygino.services.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String msg) {
        super(msg);
    }
}
