package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Programme;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProgrammeRepository extends CrudRepository<Programme, Long> {
    Iterable<Programme> findAll();
}
