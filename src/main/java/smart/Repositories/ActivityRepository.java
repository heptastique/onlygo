package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Activity;
import smart.Entities.Programme;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    Iterable<Activity> findByProgramme(Programme programme);
    Iterable<Activity> findByProgrammeAndEstRealisee(Programme programme, boolean value);
}
