package com.pontes_enterprise.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.repository.UserRepository;

//Classe auxiliar que é muito usada para por isto a funcionar
//Serve para popular o nosso banco de dados com objetos
//Primiero caso de injecao de dependencia para o userRepository
//Quando um servico depende doutra, a sua ligacao tem que ser desacopolada

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner{
    //Para que se resolva essa dependencia
    @Autowired
    private UserRepository userRepository;

    //Instancia o banco de dados e guarda os usuarios no repositorio na base de dados
    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Larry Bird", "bird@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Ronaldo Fenomeno", "fenomeno@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }//Tudo dentro deste metodo é executado quando a aplicacao começa

    //esta funcionando o mapeamento do objeto relacional e
    //a instanciacao da base de dados


}
