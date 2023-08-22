package com.pontes_enterprise.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontes_enterprise.curso.entidades.Category;


//tipo de classe e o tipo do primary key
//Nao vai ser preciso a implementacao da interface, já que o JPA ja tem uma implementacao padrap
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
