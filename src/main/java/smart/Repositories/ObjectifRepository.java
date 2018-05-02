package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Objectif;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ObjectifRepository extends CrudRepository<Objectif, Long> {
}
