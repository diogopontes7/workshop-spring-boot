package com.pontes_enterprise.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontes_enterprise.curso.entidades.Order;
import com.pontes_enterprise.curso.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired//Injeta as informacaos do repositorio ao serviço
    private OrderRepository repository;

    //Busca todas as ordens no repositorio atraves da classe serviço
    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
