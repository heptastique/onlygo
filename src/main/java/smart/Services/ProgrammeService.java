package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Entities.Programme;
import smart.Repositories.ProgrammeRepository;

import java.util.Calendar;
import java.util.Date;

@Service
public class ProgrammeService {

    @Autowired
    private ProgrammeRepository programmeRepository;

    public Programme getActiveProgrammeOfUser(Long userId){
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
        }
        Date dateDebut = calendar.getTime();
        return programmeRepository.findByUserAndDateDebut(userId, dateDebut);
    }
}
