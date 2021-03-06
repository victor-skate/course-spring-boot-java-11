package system.developer.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import system.developer.course.entities.Category;
import system.developer.course.entities.Order;
import system.developer.course.entities.OrderItem;
import system.developer.course.entities.OrderStatus;
import system.developer.course.entities.Payment;
import system.developer.course.entities.Product;
import system.developer.course.entities.User;
import system.developer.course.repositories.CategoryRepository;
import system.developer.course.repositories.OrderItemRepository;
import system.developer.course.repositories.OrderRepository;
import system.developer.course.repositories.ProductRepository;
import system.developer.course.repositories.UserRepository;

@Configuration // indica ao Spring que essa é uma classe de configuração
@Profile("test") // o nome entre aspas deve ser igual ao nome definido no arquivo application.properties
public class TestConfig implements CommandLineRunner { // commandLineRunner garante que essa classe será
	// executada quando a aplicação for iniciada

	@Autowired // resolve a dependencia e associa uma instancia do UserRepository a classe TestConfig
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {

	

		User u1 = new User(0, "Maria", "maria@gmail.com", "999999", "923847");
		User u2 = new User(0, "João", "joao@gmail.com", "8888999", "983741");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.PAID, u1);

		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 
	
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat1);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat1);
		p4.getCategories().add(cat3);		
		p5.getCategories().add(cat2);
		
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));
		
		//COMO O PAGAMENTO DEPENDE DO PEDIDO, É NECESSÁRIO SETAR O PAGAMENTO NO PEDIDO E PERSISTIR O PEDIDO
		Payment payment = new Payment(null,Instant.parse("2019-06-20T19:53:07Z"),o1);
		o1.setPayment(payment);
		
		orderRepository.save(o1);
	}

}
