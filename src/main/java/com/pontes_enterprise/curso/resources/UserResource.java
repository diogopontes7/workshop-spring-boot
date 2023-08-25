package com.pontes_enterprise.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.services.UserService;

//Controladores rest que vao ser dependes dos dados e da camada de serviço
//Controlador rest que responde sobre o caminho user

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @Autowired//Faz automaticamente a injecao de dependencia
    private UserService service;//O controlador esta dependente do servico
    //Para isto funcionar temos de registar o UserService como um componente do Spring

    //O controlador so vai mostrar o resultado obtido no serviço

    //Metodo para acessar os usuarios
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    //Servem para recuperar dados, do Get
    //Requisicao do tipo Get, a requisicao vai aceitar um Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    //Ao inserir um novo recurso, usamos o metodo HTTP POST

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Opa nem sei,vi na net, pra dar o 201 que é mais certo
        return ResponseEntity.created(uri).body(obj);//Quando vamos retormar um 201, temos o endereço do recurso que inserimos
    }
    //O 201 created é mais utilizado para requisicoes do tipo POST


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();//Como é void, ou seja sem corpo, recebemos a respostya 204
    } 
    //Falat só resolver a integridade dos dados
}
