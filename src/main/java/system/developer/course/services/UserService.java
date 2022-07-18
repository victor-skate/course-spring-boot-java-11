package system.developer.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.developer.course.entities.User;
import system.developer.course.repositories.UserRepository;

@Service // ESSA ANOTAÇÃO INDICA AO SPRING QUE ESSA ENTIDADE
//É UMA DEPENDENCIA A SER INJETADA EM ALGUMA OUTRA ENTIDADE
public class UserService {

	@Autowired // A CLASSE UserRepository RECEBEU UMA ANOTAÇÃO INDICANDO QUE ELA É UMA
	// DEPENDENCIA A SER INJETADA, E A ANOTAÇÃO Autowired NESSA INSTANCIA DO
	// UserRepository
	// FAZ A INJEÇÃO DE DEPENDENCIA AUTOMATICAMENTE
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> optional = repository.findById(id);
		return optional.get(); // VAI RETORNAR O OBJETO DO OPTIONAL, QUE NESSE CASO É DO TIPO User

	}

}
