package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Repositories.ProgrammeRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    public Programme getActiveProgrammeOfUser(User user){
        Date dateDebut = FindByJour.findFirstDayOfCurrentWeek();
        Programme programme = programmeRepository.findByUserAndDateDebut(user, dateDebut);
        programme.setUser(null);
        return programme;
    }
}
