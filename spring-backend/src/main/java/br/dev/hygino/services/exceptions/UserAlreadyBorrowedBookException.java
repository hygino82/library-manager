package br.dev.hygino.services.exceptions;

import java.io.Serial;

public class UserAlreadyBorrowedBookException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserAlreadyBorrowedBookException(String message) {
		super(message);
	}
}
