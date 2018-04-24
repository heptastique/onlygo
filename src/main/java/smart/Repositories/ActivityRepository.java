package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Activity;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Iterable<Activity> findAll();
    Optional<Activity> findById(Long id);
}
