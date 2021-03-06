package com.ah.algov.controllers.errorhandling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ah.algov.dto.StandardErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handle Bad Request for various exceptions.
	 *
	 * @param request   current request.
	 * @param exception the exception.
	 * @return a API {@link StandardErrorResponse}.
	 */
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<StandardErrorResponse> handleBadRequestError(final HttpServletRequest request,
			final Exception exception) {
		return new ResponseEntity<>(
				StandardErrorResponseFactory.create(request.getServletPath(), exception, HttpStatus.BAD_REQUEST),
				this.jsonHttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle Bad Request for various exceptions.
	 *
	 * @param request   current request.
	 * @param exception the exception.
	 * @return a API {@link StandardErrorResponse}.
	 */
	@ExceptionHandler({ CommunicationException.class })
	public ResponseEntity<StandardErrorResponse> handleInternalError(final HttpServletRequest request,
			final Exception exception) {
		return new ResponseEntity<>(StandardErrorResponseFactory.create(request.getServletPath(), exception,
				HttpStatus.INTERNAL_SERVER_ERROR), this.jsonHttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected HttpHeaders jsonHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

}