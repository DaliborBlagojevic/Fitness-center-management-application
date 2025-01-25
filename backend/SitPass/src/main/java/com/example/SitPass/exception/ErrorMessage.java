package com.example.SitPass.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;

    @JsonIgnore
    private final HttpStatus httpStatus;

    private final int statusCode;

    private final String error;

    private final String message;

    private final String path;

    public ErrorMessage(AbstractException e, HttpStatus httpStatus, String path) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.statusCode = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = e.getMessage();
        this.path = path;
    }

}
