package br.com.washington.desafio_todolist.exceptions;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException(){
        super(MessagesException.NOT_ALLOWED_TO_PERSIST_A_NULL_OBJECT);
    }

    public RequiredObjectIsNullException(String ex){
        super(ex);
    }
}
