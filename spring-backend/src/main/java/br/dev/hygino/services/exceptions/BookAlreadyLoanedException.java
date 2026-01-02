package br.dev.hygino.services.exceptions;

public class BookAlreadyLoanedException extends RuntimeException {
    public BookAlreadyLoanedException(String message) {
        super(message);
    }
}
