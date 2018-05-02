package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.DTO.RealisationDTO;
import smart.Entities.*;
import smart.Exceptions.*;
import smart.Repositories.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class RealisationService {
    @Autowired
    ProgrammeRepository programmeRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    CentreInteretRepository centreInteretRepository;

    @Autowired
    TimeFrameRepository timeFrameRepository;

    @Autowired
    ActivityService activityService;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Activity> getUserRealisations(User user){
        Iterable<Programme> programmes = programmeRepository.findByUser(user);
        List<Activity> realisations;
        realisations = new LinkedList<>();
        for (Programme p:programmes) {
            Iterable<Activity> activitiesFromRepo = activityRepository.findByProgrammeAndEstRealisee(p, true);
            for (Activity a:activitiesFromRepo
                 ) {
                realisations.add(a);
            }
        }
        return realisations;
    }

    public Activity addRealisation(RealisationDTO realisationDTO, Programme programme, Activity activity) throws RealisationException, SportException, TimeFrameException {
        float distanceRealisation = realisationDTO.getDistance();

        // Date
        Date dateRealisation = realisationDTO.getDate();
        Jour day = FindByJour.findDay(dateRealisation);
        int heureDebut = dateRealisation.getHours();

        TimeFrame timeFrame;
        CentreInteret centreInteret;

        try {
            timeFrame = timeFrameRepository.findByJourHour(day, heureDebut);
        } catch (Exception e) {
            throw new TimeFrameException("Aucune plage horaire correspondant à la date n'a été trouvée.", e);
        }

        // Associating realisation to the next (planned but not realised yet) activity
        if(activity==null)
        {
            throw new ActivityException("Plus d'activité à terminer dans le programme de la semaine.");
        }
        else
        {
            activity.setEstRealisee(true);
            activity.setDateRealisee(dateRealisation);
            activity.setDistanceRealisee(distanceRealisation);

            return activityRepository.save(activity);
        }
    }
}
