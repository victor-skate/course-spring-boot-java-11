package system.developer.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system.developer.course.entities.Order;

@Repository 
public interface OrderRepository extends JpaRepository<Order, Long>{

}
