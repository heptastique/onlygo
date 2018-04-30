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
    RealisationRepository realisationRepository;

    @Autowired
    CentreInteretRepository centreInteretRepository;

    @Autowired
    TimeFrameRepository timeFrameRepository;

    @Autowired
    ActivityService activityService;

    @Autowired
    private UserRepository userRepository;

    public Iterable<Realisation> getUserRealisations(User user){
        Iterable<Programme> programmes = programmeRepository.findByUser(user);
        List<Activity> activities;
        activities = new LinkedList<>();
        for (Programme p:programmes) {
            Iterable<Activity> activitiesFromRepo = activityRepository.findByProgramme(p);
            for (Activity a:activitiesFromRepo
                 ) {
                activities.add(a);
            }
        }
        List<Realisation> realisations = new LinkedList<>();
        for (Activity a:activities) {
            Iterable<Realisation> realisationsFromRepo = realisationRepository.findByActivite(a);
            for (Realisation r:realisationsFromRepo
                 ) {
                realisations.add(r);
            }
        }
        return realisations;
    }

    public Realisation addRealisation(RealisationDTO realisationDTO, Programme programme, Activity activity) throws RealisationException, SportException, TimeFrameException {
        float distanceRealisation = realisationDTO.getDistance();

        // Date
        Date dateRealisation = realisationDTO.getDate();
        Jour day = FindByJour.findDay(dateRealisation);
        int heureDebut = dateRealisation.getHours();

        Realisation realisation;
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
            centreInteret = activity.getCentreInteret();
            realisation = new Realisation(distanceRealisation, dateRealisation, activity, centreInteret, timeFrame);
            activity.setEstRealisee(true);
            programme.addRealisation(realisation);
            return realisationRepository.save(realisation);
        }
    }
}
