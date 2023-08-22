package com.pontes_enterprise.curso.enums;

public enum OrderStatus {

    //na base de dados, aparece enumerado, ou seja comeca de 0 para 1,´
    //Mas se um nabo mete um novo valor no meio, a base de dados fica errado,
    //pois o valor que ficou no meio vai roubar o numero de um elemento, causando confusão
    //Ent vamos manualmenete associar valores

    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    //Construtor
    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    //Percorre todos os valores
    public static OrderStatus valueOf(int code){
        for(OrderStatus value : OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }

        }
        throw new IllegalArgumentException("INVALID OrderStatus code");
    }
}
