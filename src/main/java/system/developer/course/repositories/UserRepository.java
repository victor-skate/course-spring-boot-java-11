package system.developer.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import system.developer.course.entities.User;

//nesse caso especifico não será necessário criar uma classe para implementar essa interface, pois essa 
//classe o JpaRepository a implementa automaticamente
public interface UserRepository extends JpaRepository<User, Long>{

}
