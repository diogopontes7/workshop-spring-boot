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

    public User insert(User obj){
        return repository.save(obj);//Já retorna o user
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public User update(Long id, User obj ){//O obj é o user definido pelo o usuario para saber o que atualizar,
        User entity = repository.getReferenceById(id);//Instancia o usuario, vai ser deixar um objeto monitorizada, nao vai á base de dados
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
