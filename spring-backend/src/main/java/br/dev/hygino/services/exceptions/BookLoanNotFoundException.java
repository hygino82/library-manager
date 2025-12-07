package br.dev.hygino.services.exceptions;

import java.io.Serial;

public class BookLoanNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public BookLoanNotFoundException(String message) {
		super(message);
	}
}
