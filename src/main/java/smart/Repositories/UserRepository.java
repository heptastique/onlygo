package smart.Repositories;

import org.springframework.data.repository.CrudRepository;

import smart.Entities.User;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findById(Long id);
}
