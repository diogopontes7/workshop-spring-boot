package com.pontes_enterprise.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pontes_enterprise.curso.entidades.Category;
import com.pontes_enterprise.curso.services.CategoryService;

//Controladores rest que vao ser dependes dos dados e da camada de serviço
//Controlador rest que responde sobre o caminho Category

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
    
    @Autowired//Faz automaticamente a injecao de dependencia
    private CategoryService service;//O controlador esta dependente do servico
    //Para isto funcionar temos de registar o CategoryService como um componente do Spring

    //O controlador so vai mostrar o resultado obtido no serviço

    //Metodo para acessar os usuarios
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Requisicao do tipo Get, a requisicao vai aceitar um Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
