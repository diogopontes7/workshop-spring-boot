package com.pontes_enterprise.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontes_enterprise.curso.entidades.Category;
import com.pontes_enterprise.curso.repository.CategoryRepository;
import com.pontes_enterprise.curso.services.exceptions.ResourceNotFoundException;

@Service//Componente do spring do tipo Service, agora vai dar o AutoWired
public class CategoryService {
    @Autowired
    private CategoryRepository repository;//Dependencia criada, serviço depende de repositorios
    //Neste ja nao é necessário pq esta herdando do JpaRepository

    //Isto busca no repositorio todos os usuarios, atraves dda classe servico
    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));//Obter o valor do obj
    }

    public Category insert(Category obj){
        return repository.save(obj);
    }


    public void delete(Long id){

    }
}
