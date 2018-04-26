package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Repositories.ProgrammeRepository;

import java.text.DateFormat;
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
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
        }
        Date dateFull = calendar.getTime();
        //SimpleDateFormat dateFormatEntry = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateS = dateFormat.format(dateFull);
        int day = dateFull.getDay();
        int month = dateFull.getMonth();
        int year = dateFull.getYear();
        Date dateDebut = null;
        try {
            dateDebut = dateFormat.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //return programmeRepository.findByUserAndDateDebut(user, dateFull);
        return programmeRepository.findByDateDebut(dateDebut);
    }
}
