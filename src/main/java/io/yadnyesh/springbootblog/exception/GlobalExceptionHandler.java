package io.yadnyesh.springbootblog.exception;

import io.yadnyesh.springbootblog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException
            (ResourceNotFoundException resourceNotFoundException,
             WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                                                     resourceNotFoundException.getMessage(),
                                                     webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handleBlogApiExceptionn
            (BlogApiException blogApiException,
             WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                blogApiException.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
