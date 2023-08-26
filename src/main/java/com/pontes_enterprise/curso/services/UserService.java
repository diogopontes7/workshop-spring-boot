package com.pontes_enterprise.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.repository.UserRepository;
import com.pontes_enterprise.curso.services.exceptions.DatabaseException;
import com.pontes_enterprise.curso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
        //return obj.get();//Obter o valor do obj e se houver um erro devolver sempre o erro 500
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
        //Vai tentar dar get, mas se nao conseguir devolver a mensagem de erro da excecao
    }

    public User insert(User obj){
        return repository.save(obj);//Já retorna o user
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {//Vamos querer capturar essa excecao, ja que quando metemos o valor errado, aparece essa excecao
            throw new ResourceNotFoundException(id);//Nao da o erro 500, aparece a excecao EmptyResultDataException
            //Excecao que criamos, que aparece a mensagem que criamos
        }  catch(DataIntegrityViolationException e){//Ou seja, quando queremos apagar um user que tem pedidos associados
            throw new DatabaseException(e.getMessage());
        } 
        


        //catch(RuntimeException e){//Qualquer runtimeException que aparece, vamos rodar na console
            //e.printStackTrace();
        //}
    }

    public User update(Long id, User obj ){//O obj é o user definido pelo o usuario para saber o que atualizar,

        try {
            User entity = repository.getReferenceById(id);//Instancia o usuario, vai ser deixar um objeto monitorizada, nao vai á base de dados
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {//Entidade nao encontrada, ou seja usuario nao existe, nao da pra atualizar lo
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
