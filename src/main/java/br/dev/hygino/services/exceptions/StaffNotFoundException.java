package br.dev.hygino.services.exceptions;

public class StaffNotFoundException extends RuntimeException {

    public StaffNotFoundException(String msg) {
        super(msg);
    }
}
