package smart.Repositories;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;
import smart.Entities.PointCentreInteret;

public interface PointCentreInteretRepository extends CrudRepository<PointCentreInteret, Long>{
    Iterable<PointCentreInteret> findAll();
}

