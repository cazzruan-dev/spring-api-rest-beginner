package com.github.cazzruandev.springapirestbeginner.api.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {

    private String message;
    private String field;
    private Object parameter;

}
