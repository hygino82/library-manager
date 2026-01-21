package br.dev.hygino.services.exceptions;

public class BorrowBookException extends RuntimeException {
    public BorrowBookException(String message) {
        super(message);
    }
}
