package br.dev.hygino.services.exceptions;

import java.io.Serial;

public class BorrowBookException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public BorrowBookException(String message) {
		super(message);
	}
}
