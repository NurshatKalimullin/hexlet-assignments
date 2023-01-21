package exercise.repository;

import exercise.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


// BEGIN
@Repository
// Реализуем интерфейс ReactiveCrudRepository
public interface UserRepository extends ReactiveCrudRepository<User, Integer>{
}
// END
