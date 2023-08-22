package com.pontes_enterprise.curso.entidades;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pontes_enterprise.curso.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//@JsonIgnore se queremos todos os pedidos associados a um cliente, tiramos o jsonIgnore no user
//Nao recomendado, já que pode estourar a memoria
@Entity
@Table(name = "tb_order")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;//Na base de dados, queremos o codigo

    //Para dizer que isto é uma chave estrangeira, de muitos pra um
    @ManyToOne
    @JoinColumn(name = "client_id")//Nome que vai aparecer na tabela
    private User client;
    //Uma ordem esta associada a um cliente

    public Order(){}

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);//definimos o orderstatus
        this.client = client;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

     public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }//Queremos o codigo do orderstatus

    public void setOrderStatus(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }//Se existir, definimos atraves do valor
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((moment == null) ? 0 : moment.hashCode());
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (moment == null) {
            if (other.moment != null)
                return false;
        } else if (!moment.equals(other.moment))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        return true;
    }
    
}
