package com.pontes_enterprise.curso.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pontes_enterprise.curso.services.exceptions.DatabaseException;
import com.pontes_enterprise.curso.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

//Excecao onde vamos fazer o tratamento manual dos nossos erros

@ControllerAdvice
public class ResourceExceptionHandler {

    //Para que seja capaz de intercetar onde deu a exceção para cair neste método
    @ExceptionHandler(ResourceNotFoundException.class)//Vai intersetar qualquer tipo de excecao desta classe e tratar la neste metodo 
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,HttpServletRequest request ){//Pedido para descobrir o id
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;//O erro do http por nao por o id direito vai ser o 404 not found
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());//Definimos o erro manualmente
        return ResponseEntity.status(status).body(err);//Defini se é ok ou nao e devolve o corpo da resposta o err
    }

    @ExceptionHandler(DatabaseException.class)//Vai intersetar qualquer tipo de excecao desta classe e tratar la neste metodo 
    public ResponseEntity<StandardError> database(DatabaseException e,HttpServletRequest request ){//Pedido para descobrir o id
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;//Pedir para eliminar algo que tem objetos associados, não é boa ideia, erro 400 bad Request
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());//Definimos o erro manualmente
        return ResponseEntity.status(status).body(err);//Defini se é ok ou nao e devolve o corpo da resposta o err
    }
}
