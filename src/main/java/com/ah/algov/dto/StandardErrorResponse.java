package com.ah.algov.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An error response that would hold exception details.
 */
public class StandardErrorResponse {
    @JsonProperty("timestamp")
    private BigDecimal timestamp = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("error")
    private String error = null;

    @JsonProperty("exception")
    private String exception = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("path")
    private String path = null;

    public StandardErrorResponse timestamp(final BigDecimal timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public BigDecimal getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final BigDecimal timestamp) {
        this.timestamp = timestamp;
    }

    public StandardErrorResponse status(final Integer status) {
        this.status = status;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public StandardErrorResponse error(final String error) {
        this.error = error;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public StandardErrorResponse exception(final String exception) {
        this.exception = exception;
        return this;
    }

    public String getException() {
        return exception;
    }

    public void setException(final String exception) {
        this.exception = exception;
    }

    public StandardErrorResponse message(final String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public StandardErrorResponse path(final String path) {
        this.path = path;
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }
}
