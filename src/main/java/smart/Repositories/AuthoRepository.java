package smart.Repositories;

import org.springframework.data.repository.CrudRepository;

import smart.Entities.Authority;
import smart.Entities.AuthorityName;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AuthoRepository extends CrudRepository<Authority, Long> {
    Authority findById(long id);
    Authority findByName(AuthorityName name);
}
