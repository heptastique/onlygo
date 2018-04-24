package smart.Repositories;

import org.springframework.data.repository.CrudRepository;

import smart.Entities.Jour;
import smart.Entities.TimeFrame;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TimeFrameRepository extends CrudRepository<TimeFrame, Long> {
    Iterable<TimeFrame> findByJour(Jour jour);
}
