package br.dev.hygino.services.exceptions;

import java.io.Serial;

public class UserHasBookLoanException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserHasBookLoanException(String message) {
		super(message);
	}
}
