package com.github.cazzruandev.springapirestbeginner.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String message) {
        super(message);
    }

}
