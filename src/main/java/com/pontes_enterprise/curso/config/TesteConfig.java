package com.pontes_enterprise.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pontes_enterprise.curso.entidades.Category;
import com.pontes_enterprise.curso.entidades.Order;
import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.enums.OrderStatus;
import com.pontes_enterprise.curso.repository.CategoryRepository;
import com.pontes_enterprise.curso.repository.OrderRepository;
import com.pontes_enterprise.curso.repository.UserRepository;

//Classe auxiliar que é muito usada para por isto a funcionar
//Serve para popular o nosso banco de dados com objetos
//Primiero caso de injecao de dependencia para o userRepository
//Quando um servico depende doutra, a sua ligacao tem que ser desacopolada

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
    // Para que se resolva essa dependencia
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Instancia o banco de dados e guarda os usuarios no repositorio na base de
    // dados

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        User u1 = new User(null, "Larry Bird", "bird@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Ronaldo Fenomeno", "fenomeno@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
    // Tudo dentro deste metodo é executado quando a aplicacao começa

    // esta funcionando o mapeamento do objeto relacional e
    // a instanciacao da base de dados

}
