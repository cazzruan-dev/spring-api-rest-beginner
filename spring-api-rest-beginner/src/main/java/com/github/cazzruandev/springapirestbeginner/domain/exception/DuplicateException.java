package com.github.cazzruandev.springapirestbeginner.domain.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }

}
