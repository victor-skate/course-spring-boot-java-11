package system.developer.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import system.developer.course.entities.Order;
import system.developer.course.entities.User;
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
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(0, "Maria", "maria@gmail.com", "999999", "923847");
		User u2 = new User(0, "João", "joao@gmail.com", "8888999", "983741");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1,o2,o3));	
	}

}
