package com.example.SitPass.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    public GlobalExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> handleUnauthorizedException(UnauthorizedException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorMessage> handleInternalServerException(InternalServerException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorMessageBuilder.append(((FieldError) error).getField()).append(": ").append(error.getDefaultMessage()).append("; ");
        });
        return handleBadRequestException(new BadRequestException(errorMessageBuilder.toString()));
    }

    private ResponseEntity<ErrorMessage> createResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(AbstractException exception, HttpStatus httpStatus) {
        return new ErrorMessage(exception, httpStatus, getPath(request));
    }

    private String getPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

}