package com.pontes_enterprise.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontes_enterprise.curso.entidades.Product;
import com.pontes_enterprise.curso.repository.ProductRepository;
//estas classes do service e resource sao feitas para atraves do postman ou, posteriormente, da web, obtermos os recursos de cada class

@Service//Componente do spring do tipo Service, agora vai dar o AutoWired
public class ProductService {
    @Autowired
    private ProductRepository repository;//Dependencia criada, serviço depende de repositorios
    //Neste ja nao é necessário pq esta herdando do JpaRepository

    //Isto busca no repositorio todos os usuarios, atraves dda classe servico
    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();//Obter o valor do obj
    }
}
