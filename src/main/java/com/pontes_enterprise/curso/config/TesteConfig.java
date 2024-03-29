package com.pontes_enterprise.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pontes_enterprise.curso.entidades.Category;
import com.pontes_enterprise.curso.entidades.Order;
import com.pontes_enterprise.curso.entidades.OrderItem;
import com.pontes_enterprise.curso.entidades.Payment;
import com.pontes_enterprise.curso.entidades.Product;
import com.pontes_enterprise.curso.entidades.User;
import com.pontes_enterprise.curso.enums.OrderStatus;
import com.pontes_enterprise.curso.repository.CategoryRepository;
import com.pontes_enterprise.curso.repository.OrderItemRepository;
import com.pontes_enterprise.curso.repository.OrderRepository;
import com.pontes_enterprise.curso.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Instancia o banco de dados e guarda os usuarios no repositorio na base de
    // dados

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p2.getCategories().add(cat3);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		User u1 = new User(null, "Larry Bird", "bird@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Ronaldo Fenonemo", "fenonemo@gmail.com", "977777777", "123456");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));	
        
        Payment pr1 = new Payment(null,Instant.parse("2019-06-20T19:53:07Z") , o1);//nao vai ter repostiorio nem servico pq pertence a ordem, por assim dizer
        o1.setPayment(pr1);
        orderRepository.save(o1);
    }
    // Tudo dentro deste metodo é executado quando a aplicacao começa

    // esta funcionando o mapeamento do objeto relacional e
    // a instanciacao da base de dados

}
