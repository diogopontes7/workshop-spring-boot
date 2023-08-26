package com.pontes_enterprise.curso.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pontes_enterprise.curso.entidades.Product;

import com.pontes_enterprise.curso.services.ProductService;

//Controladores rest que vao ser dependes dos dados e da camada de serviço
//Controlador rest que responde sobre o caminho Product

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    
    @Autowired//Faz automaticamente a injecao de dependencia
    private ProductService service;//O controlador esta dependente do servico
    //Para isto funcionar temos de registar o ProductService como um componente do Spring

    //O controlador so vai mostrar o resultado obtido no serviço

    //Metodo para acessar os usuarios
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //Requisicao do tipo Get, a requisicao vai aceitar um Id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//Opa nem sei,vi na net, pra dar o 201 que é mais certo
        return ResponseEntity.created(uri).body(obj);//Quando vamos retormar um 201, temos o endereço do recurso que inserimos
    }
}
