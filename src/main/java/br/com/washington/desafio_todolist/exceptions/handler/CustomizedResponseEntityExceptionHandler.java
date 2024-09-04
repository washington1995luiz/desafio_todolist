package br.com.washington.desafio_todolist.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.washington.desafio_todolist.exceptions.ExceptionResponse;
import br.com.washington.desafio_todolist.exceptions.RequiredObjectIsNullException;
import br.com.washington.desafio_todolist.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
     @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllExceptions(
        Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = 
                new ExceptionResponse(
                    new Date(), 
                    ex.getMessage(), 
                    request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(
        Exception ex, WebRequest request){
            ExceptionResponse exceptionResponse = 
                new ExceptionResponse(
                    new Date(), 
                    ex.getMessage(), 
                    request.getDescription(false));
        
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> HandleBadRequestException(
            Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        ex.getMessage(),
                        request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
