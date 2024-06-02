package br.dev.hygino.services.exceptions;

public class LoanNotFoundException extends RuntimeException {
    
    public LoanNotFoundException(String msg) {
        super(msg);
    }
}
