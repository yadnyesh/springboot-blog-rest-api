package io.yadnyesh.springbootblog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public BlogApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public BlogApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
