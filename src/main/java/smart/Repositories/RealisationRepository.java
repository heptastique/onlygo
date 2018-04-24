package smart.Repositories;

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Realisation;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RealisationRepository extends CrudRepository<Realisation, Long> {
    Iterable<Realisation> findAll();
    Optional<Realisation> findById(Long id);
}
