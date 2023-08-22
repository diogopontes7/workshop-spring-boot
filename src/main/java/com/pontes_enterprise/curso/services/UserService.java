package com.pontes_enterprise.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.repository.UserRepository;

@Service//Componente do spring do tipo Service, agora vai dar o AutoWired
public class UserService {
    @Autowired
    private UserRepository repository;//Dependencia criada, serviço depende de repositorios
    //Neste ja nao é necessário pq esta herdando do JpaRepository

    //Isto busca no repositorio todos os usuarios, atraves dda classe servico
    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.get();//Obter o valor do obj
    }
}
