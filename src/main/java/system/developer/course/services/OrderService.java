package system.developer.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.developer.course.entities.Order;
import system.developer.course.repositories.OrderRepository;

@Service // ESSA ANOTAÇÃO INDICA AO SPRING QUE ESSA ENTIDADE
//É UMA DEPENDENCIA A SER INJETADA EM ALGUMA OUTRA ENTIDADE
public class OrderService {

	@Autowired // A CLASSE OrderRepository RECEBEU UMA ANOTAÇÃO INDICANDO QUE ELA É UMA
	// DEPENDENCIA A SER INJETADA, E A ANOTAÇÃO Autowired NESSA INSTANCIA DO
	// OrderRepository
	// FAZ A INJEÇÃO DE DEPENDENCIA AUTOMATICAMENTE
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> optional = repository.findById(id);
		return optional.get(); // VAI RETORNAR O OBJETO DO OPTIONAL, QUE NESSE CASO É DO TIPO Order

	}

}
