package com.restful.restfulwebservices.exception;

import com.restful.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice // we want to make it applicable across all other controllers
@RestController // since it has to send response back
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class) // this method will be called for all the exceptions
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){

        ExceptionResponse exc = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exc, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) // this method will be called for all USerNotFound exceptions
    public final ResponseEntity<Object> handleUSerNotFoundExceptions(Exception ex, WebRequest request){

        ExceptionResponse exc = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity(exc, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ExceptionResponse exc = new ExceptionResponse(new Date(),ex.getMessage(),ex.getBindingResult().toString());
        return new ResponseEntity(exc, HttpStatus.BAD_REQUEST);
    }

}
