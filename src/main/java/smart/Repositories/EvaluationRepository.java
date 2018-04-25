package smart.Repositories;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;
import smart.Entities.Evaluation;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long> {
    Iterable<Evaluation> findAll();
}
