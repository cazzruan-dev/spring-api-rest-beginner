package com.github.cazzruandev.springapirestbeginner.domain.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message){
        super(message);
    }

}