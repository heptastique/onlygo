package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smart.Entities.Evaluation;
import smart.Entities.Jour;
import smart.Entities.TimeFrame;
import smart.Repositories.EvaluationRepository;
import smart.Repositories.TimeFrameRepository;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private TimeFrameRepository timeFrameRepository;

    public Iterable <Evaluation> getEvaluationAll() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluation(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        int startHour;
        if (time.equals("now")) {
            date = new Date();
            startHour = date.getHours();
        } else {
            String day = time.substring(0, 10);
            try {
                date = sdf.parse(day);
            } catch (Exception e) {
                date = null;
            }
            startHour = Integer.parseInt(time.substring(11, 13));
        }
        Jour jour;
        switch(date.getDay())
        {
            case 0 :
                jour = Jour.DIMANCHE;
                break;
            case 1 :
                jour = Jour.LUNDI;
                break;
            case 2 :
                jour = Jour.MARDI;
                break;
            case 3 :
                jour = Jour.MERCREDI;
                break;
            case 4 :
                jour = Jour.JEUDI;
                break;
            case 5 :
                jour = Jour.VENDREDI;
                break;
            case 6 :
                jour = Jour.SAMEDI;
                break;
            default :
                jour = Jour.LUNDI;
                break;
        }
        return null;
    }
}
