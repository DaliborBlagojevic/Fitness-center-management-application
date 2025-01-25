package com.example.SitPass.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends AbstractException {

    public NotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public NotFoundException(String message, String path) {
        super(message, path);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

}
