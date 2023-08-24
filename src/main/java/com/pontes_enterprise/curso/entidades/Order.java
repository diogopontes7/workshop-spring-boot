package com.pontes_enterprise.curso.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


import com.pontes_enterprise.curso.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//@JsonIgnore se queremos todos os pedidos associados a um cliente, tiramos o jsonIgnore no user
//Nao recomendado, já que pode estourar a memoria
@Entity
@Table(name = "tb_order")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    private Integer orderStatus;//Na base de dados, queremos o codigo

    //Para dizer que isto é uma chave estrangeira, de muitos pra um
    @ManyToOne
    @JoinColumn(name = "client_id")//Nome que vai aparecer na tabela
    private User client;
    //Uma ordem esta associada a um cliente


    @OneToMany(mappedBy = "id.order")//no orderitem temos o id que lá tem o pedido e o produto, aqui pedimos o pedido
    private Set<OrderItem> items = new HashSet<>();

    //No caso de 1 para 1 para mapeamos os dois, para ter o mesmo id, caso o pedido seja 5, no pagamento o id de pedido
    //tmb vai ser 5, é obrigatorio, com o mesmo id meter o cascade
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

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

    public Payment getPayment(){
        return payment;
    }

    public void setPayment(Payment payment){
        this.payment= payment;
    }

    public Set<OrderItem> getItems(){
        return items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        return true;
    }


    
    
}
