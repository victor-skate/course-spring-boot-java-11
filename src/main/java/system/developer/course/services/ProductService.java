package system.developer.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.developer.course.entities.Product;
import system.developer.course.repositories.ProductRepository;

@Service // ESSA ANOTAÇÃO INDICA AO SPRING QUE ESSA ENTIDADE
//É UMA DEPENDENCIA A SER INJETADA EM ALGUMA OUTRA ENTIDADE
public class ProductService {

	@Autowired // A CLASSE ProductRepository RECEBEU UMA ANOTAÇÃO INDICANDO QUE ELA É UMA
	// DEPENDENCIA A SER INJETADA, E A ANOTAÇÃO Autowired NESSA INSTANCIA DO
	// ProductRepository
	// FAZ A INJEÇÃO DE DEPENDENCIA AUTOMATICAMENTE
	private ProductRepository repository;

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product findById(Long id) {
		Optional<Product> optional = repository.findById(id);
		return optional.get(); // VAI RETORNAR O OBJETO DO OPTIONAL, QUE NESSE CASO É DO TIPO Product

	}

}
