package exercise.repository;

import exercise.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    Iterable<City> findByNameStartingWithIgnoreCase(String letters);

    Iterable<City> findAllByOrderByName();
    // END
}
