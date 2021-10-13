package system.developer.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import system.developer.course.entities.User;

@RestController
@RequestMapping(value= "/users")
public class UserResource {
	
	@GetMapping
	//ResponseEntity<T> TIPO ESPECIFICO DO SPRING PARA RETORNAR RESPOSTAS DE REQUISIÇÕES WEB
	public ResponseEntity<User> findAll(){
		User u = new User(1,"Maria","Maria@gmail.com","65994384758","12345");
		return ResponseEntity.ok().body(u);
	}
		
}
