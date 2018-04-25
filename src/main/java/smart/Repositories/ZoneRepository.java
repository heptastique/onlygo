package smart.Repositories;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Zone;


public interface ZoneRepository extends CrudRepository<Zone, Long> {
    Iterable<Zone> findAll();
}

