package com.pontes_enterprise.curso.services.exceptions;

//Excecao que vai ser utilizada para quando o id introduzido nao exista na base de dados, em vez de dar
//O erro 500 que é sinal de erro no servidor que é mau sinal, vamos dar o erro http 404 que é Not Found
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id){
        super("Resource not Found .Id " +id);
    }
    //Subclasse de runTime exception, criamos o construtor para aceitar qualquer obejto e mandar uma mensagem do tipo super 
    //Como é uma resposta de pedido, metemos esta excecao no services
}
