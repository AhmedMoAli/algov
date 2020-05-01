package com.ah.algov.algorithms.controllers.errorhandling;

import java.math.BigDecimal;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.ah.algov.algorithms.dto.StandardErrorResponse;

/**
 * The standard error response factory class.
 */
public final class StandardErrorResponseFactory {

	private StandardErrorResponseFactory() {
		// no instances
	}

	/**
	 * Creates a {@link StandardErrorResponseFactory} from the given exception and
	 * status.
	 * 
	 * @param path      current request path.
	 * @param exception the exception
	 * @param status    the http status
	 * @return a new {@link StandardErrorResponseFactory}.
	 */
	public static StandardErrorResponse create(final String path, final Exception exception, final HttpStatus status) {
		if (status == HttpStatus.SERVICE_UNAVAILABLE) {
			LoggerFactory.getLogger(StandardErrorResponseFactory.class)
					.warn("Unable to process request (Service Unavailable).", exception);
		} else if (status.is5xxServerError()) {
			LoggerFactory.getLogger(StandardErrorResponseFactory.class).error("Error in AlgoV api call.", exception);
		} else {
			LoggerFactory.getLogger(StandardErrorResponseFactory.class).info("AlgoV api call failed: {}", exception);
		}
		return new StandardErrorResponse().status(status.value()).message(status.getReasonPhrase())
				.exception(exception.getClass().getName()).error(exception.getMessage()).path(path)
				.timestamp(new BigDecimal(System.currentTimeMillis()));
	}
}
