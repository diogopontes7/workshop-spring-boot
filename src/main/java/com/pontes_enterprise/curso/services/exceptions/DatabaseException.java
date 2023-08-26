package com.pontes_enterprise.curso.services.exceptions;

public class DatabaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DatabaseException(String msg){
        super(msg);
    }//Vai gerar uma excecao com a mensagem 
}
