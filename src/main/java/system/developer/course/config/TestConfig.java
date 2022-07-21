package system.developer.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import system.developer.course.entities.Category;
import system.developer.course.entities.Order;
import system.developer.course.entities.OrderStatus;
import system.developer.course.entities.User;
import system.developer.course.repositories.CategoryRepository;
import system.developer.course.repositories.OrderRepository;
import system.developer.course.repositories.UserRepository;

@Configuration // indica ao Spring que essa é uma classe de configuração
@Profile("test") // o nome entre aspas deve ser igual ao nome definido no arquivo
					// application.properties
public class TestConfig implements CommandLineRunner { // commandLineRunner garante que essa classe será
	// executada quando a aplicação for iniciada

	@Autowired // resolve a dependencia e associa uma instancia do UserRepository a classe
				// TestConfig
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");

		User u1 = new User(0, "Maria", "maria@gmail.com", "999999", "923847");
		User u2 = new User(0, "João", "joao@gmail.com", "8888999", "983741");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAID, u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	}

}
