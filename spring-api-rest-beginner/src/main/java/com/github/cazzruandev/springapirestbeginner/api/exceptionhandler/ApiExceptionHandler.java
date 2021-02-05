package com.github.cazzruandev.springapirestbeginner.api.exceptionhandler;

import com.github.cazzruandev.springapirestbeginner.domain.exception.DuplicateException;
import com.github.cazzruandev.springapirestbeginner.domain.exception.EntityNotFoundException;
import com.github.cazzruandev.springapirestbeginner.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private List<ErrorDetail> getErrorDetail(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorDetail(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    private ApiStandardError getApiStandardError(Integer status, String error, String message, String URI, List<ErrorDetail> errors){
        return new ApiStandardError(LocalDateTime.now(), status, error, message, URI, errors);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorDetail> errors = getErrorDetail(ex);
        ServletWebRequest swrequest = (ServletWebRequest) request;
        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Validation Failed",
                "Validation failed for argument(s)", swrequest.getRequest().getRequestURI(), errors );
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegocioException.class)
    private ResponseEntity<ApiStandardError> handleNegocioException(DuplicateException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Action Failed",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private  ResponseEntity<ApiStandardError> handleEntityNotFoundException(EntityNotFoundException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.NOT_FOUND.value(), "Entity Not Found",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    private ResponseEntity<ApiStandardError> handleDuplicateException(DuplicateException ex, ServletWebRequest swrequest){
        ApiStandardError error = getApiStandardError(HttpStatus.BAD_REQUEST.value(), "Duplicated Value",
                ex.getMessage(), swrequest.getRequest().getRequestURI(), null);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
