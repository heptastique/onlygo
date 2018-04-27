package smart.Algorithms;

import smart.Entities.Jour;

import java.util.Calendar;
import java.util.Date;

public class FindByJour {

    public static Jour findDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(calendar.get(Calendar.DAY_OF_WEEK))
        {
            case 0 :
                return Jour.DIMANCHE;
            case 1 :
                return Jour.LUNDI;
            case 2 :
                return Jour.MARDI;
            case 3 :
                return Jour.MERCREDI;
            case 4 :
                return Jour.JEUDI;
            case 5 :
                return Jour.VENDREDI;
            case 6 :
                return Jour.SAMEDI;
            default :
                return Jour.LUNDI;
        }
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
