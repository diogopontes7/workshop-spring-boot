package com.pontes_enterprise.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontes_enterprise.curso.entidades.User;

//Controladores rest que vao ser dependes dos dados e da camada de serviço
//Controlador rest que responde sobre o caminho user

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    //Metodo para acessar os usuarios
    @GetMapping
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Lebron", "lebron@gmail.com", "919191919", "12345");
        return ResponseEntity.ok().body(u);
    }
}
