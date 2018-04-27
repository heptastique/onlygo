package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.DTO.RealisationDTO;
import smart.Entities.*;
import smart.Exceptions.RealisationException;
import smart.Repositories.ActivityRepository;
import smart.Repositories.ProgrammeRepository;
import smart.Repositories.RealisationRepository;
import smart.Repositories.SportRepository;

import java.util.Date;

@Service
public class RealisationService {

    @Autowired
    private RealisationRepository realisationRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private ProgrammeRepository programmeRepository;

    public Realisation addRealisation(RealisationDTO realisationDTO, User user) throws RealisationException{
        Long sportId = realisationDTO.getSportId();
        Long activityId = realisationDTO.getActivityId();
        Date dateRealisation = realisationDTO.getDate();
        float distanceRealisation = realisationDTO.getDistance();
        Sport sport = sportRepository.findById(sportId).get();
        Date dateDebut = FindByJour.findFirstDayOfCurrentWeek();
        Programme programme = programmeRepository.findByUserAndDateDebut(user, dateDebut);
        Activity activity;
        Realisation realisation;

        // Associating realisation to a planned and not realised yet activity
        if(activityId!=null)
        {
            try {
                activity = activityRepository.findByIdAndEstRealisee(activityId, false);
                realisation = new Realisation(distanceRealisation, dateRealisation, activity);
                activity.setEstRealisee(true);
                return realisationRepository.save(realisation);
            } catch(Exception e)
            {
                throw new RealisationException("L'activité associée n'a pas été trouvée.", e);
            }
        }
        // Creating a new activity for the realisation
        else
        {
            activity = new Activity(sport, distanceRealisation, programme, dateRealisation, true);
            realisation = new Realisation(distanceRealisation, dateRealisation, activity);
            return realisationRepository.save(realisation);
        }
    }
}
