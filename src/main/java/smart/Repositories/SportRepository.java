package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Sport;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SportRepository extends CrudRepository<Sport, Long> {
    Iterable<Sport> findAll();
}
