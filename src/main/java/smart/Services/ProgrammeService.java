package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
        }
        Date dateFull = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateS = dateFormat.format(dateFull);
        Date dateDebut = null;
        try {
            dateDebut = dateFormat.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Programme programme = programmeRepository.findByUserAndDateDebut(user, dateDebut);
        programme.setUser(null);
        return programme;
    }
}
