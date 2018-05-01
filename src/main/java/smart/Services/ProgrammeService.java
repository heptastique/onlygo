package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Exceptions.ProgrammeException;
import smart.Repositories.ProgrammeRepository;
import smart.Repositories.UserRepository;

import java.util.Date;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    @Autowired
    private UserRepository userRepository;

    public Programme getActiveProgrammeOfUser(String username) throws ProgrammeException {
        User user = userRepository.findByUsername(username);
        Date dateDebut = FindByJour.findFirstDayOfCurrentWeek();
        Programme programme;

        try {
            programme = programmeRepository.findByUserAndDateDebut(user, dateDebut).get();
        } catch(Exception e)
        {
            return null;
        }

        return programme;
    }

    public Programme getProgramOfUserByDate(String username, Date dateDebut) throws ProgrammeException {
        User user = userRepository.findByUsername(username);
        Programme programme;

        try {
            programme = programmeRepository.findByUserAndDateDebut(user, dateDebut).get();
        } catch(Exception e)
        {
            throw new ProgrammeException("Aucun programme n'a été trouvé pour cette date.", e);
        }

        return programme;
    }

    public Programme saveProgram(Programme program) {
        return programmeRepository.save(program);
    }
}
