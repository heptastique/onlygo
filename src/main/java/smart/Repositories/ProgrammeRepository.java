package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Programme;
import smart.Entities.User;

import java.util.Date;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProgrammeRepository extends CrudRepository<Programme, Long> {
    Optional<Programme> findByUserAndDateDebut(User user, Date dateDebut);
    Iterable<Programme> findByUser(User user);
}
