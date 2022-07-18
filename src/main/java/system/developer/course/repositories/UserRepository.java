package system.developer.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system.developer.course.entities.User;

//nesse caso especifico não será necessário criar uma classe para implementar essa interface, pois essa 
//classe o JpaRepository a implementa automaticamente
@Repository // ESSA ANOTAÇÃO É OPCIONAL, POIS ESSA CLASSE extends JpaRepository QUE 
//JÁ POSSUI A ANOTAÇÃO @Repository
public interface UserRepository extends JpaRepository<User, Long>{
//A JpaRepository JÁ TEM AS IMPLEMENTAÇÕES DAS OPERAÇOES SQL
}
