package smart.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import smart.Entities.Programme;
import smart.Entities.User;

import java.util.Date;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProgrammeRepository extends CrudRepository<Programme, Long> {
    /*Iterable<Programme> findAll();
    Optional<Programme> findById(Long id);*/
    //Programme findByUser(User user);
    Programme findByUserAndDateDebut(User user, Date dateDebut);
    //@Query("SELECT programme FROM Programme programme WHERE programme.dateDebut = ?1")
    Programme findByDateDebut(Date datedebut);
}
