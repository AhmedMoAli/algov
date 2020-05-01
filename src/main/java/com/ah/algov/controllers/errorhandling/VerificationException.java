package com.ah.algov.controllers.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * Runtime wrapper for exception handling.
 * 
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class VerificationException extends RuntimeException {

	private static final long serialVersionUID = 3135913252336393690L;

	/**
	 * Constructor.
	 *
	 * @param message the error message.
	 */
	public VerificationException(final String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message the error message.
	 * @param cause   the cause.
	 */
	public VerificationException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
