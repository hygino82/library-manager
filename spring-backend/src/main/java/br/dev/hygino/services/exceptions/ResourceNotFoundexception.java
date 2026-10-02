package br.dev.hygino.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundexception extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundexception(String message) {
		super(message);
	}
}
