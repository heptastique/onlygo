package smart.Algorithms;

import org.springframework.stereotype.Service;
import smart.Entities.Jour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class FindByJour {

    public static Jour findDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(calendar.get(Calendar.DAY_OF_WEEK))
        {
            case 1 :
                return Jour.DIMANCHE;
            case 2 :
                return Jour.LUNDI;
            case 3 :
                return Jour.MARDI;
            case 4 :
                return Jour.MERCREDI;
            case 5 :
                return Jour.JEUDI;
            case 6 :
                return Jour.VENDREDI;
            case 7 :
                return Jour.SAMEDI;
            default :
                return Jour.LUNDI;
        }
    }

    public static Date findFirstDayOfCurrentWeek()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
        }
        Date dateFull = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateS = dateFormat.format(dateFull);
        Date dateDebut = null;
        try {
            dateDebut = dateFormat.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateDebut;
    }

    private FindByJour()
    {}

    /** Instance unique pré-initialisée */
    private static FindByJour INSTANCE = new FindByJour();

    /** Point d'accès pour l'instance unique du singleton */
    public static FindByJour getInstance()
    {   return INSTANCE;
    }
}
