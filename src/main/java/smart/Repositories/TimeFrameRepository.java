package smart.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import smart.Entities.Jour;
import smart.Entities.TimeFrame;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TimeFrameRepository extends CrudRepository<TimeFrame, Long> {
    Iterable<TimeFrame> findByJour(Jour jour);
    @Query("SELECT timeFrame FROM TimeFrame timeFrame WHERE timeFrame.jour = ?1 AND timeFrame.heureDebut <= ?2 AND timeFrame.heureFin > ?2")
    TimeFrame findByJourHour(Jour jour, int startHour);
}
