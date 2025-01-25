package com.example.SitPass.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServerException extends AbstractException {

    public InternalServerException(String message) {
        super(message);
        log.error(message);
    }

    public InternalServerException(String message, String path) {
        super(message, path);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }

}