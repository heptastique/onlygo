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
    SportRepository sportRepository;

    @Autowired
    CentreInteretRepository centreInteretRepository;

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

    public Realisation addRealisation(RealisationDTO realisationDTO, User user) throws RealisationException, SportException, ProgrammeException, ActivityException, CentreInteretException {
        Long sportId = realisationDTO.getSportId();
        Long activityId = realisationDTO.getActivityId();
        Long centreinteretId = realisationDTO.getCiId();
        Date dateRealisation = realisationDTO.getDate();
        float distanceRealisation = realisationDTO.getDistance();
        Sport sport;
        Programme programme;
        CentreInteret centreInteret = null;
        Activity activity;
        Realisation realisation;
        try {
            sport = sportRepository.findById(sportId).get();
        } catch (Exception e) {
            throw new SportException("Le sport sélectionné n'existe pas.", e);
        }
        Date dateDebut = FindByJour.findFirstDayOfCurrentWeek();
        try {
            programme = programmeRepository.findByUserAndDateDebut(user, dateDebut);
        } catch (Exception e) {
            throw new ProgrammeException("Aucun programme actif trouvé pour l'utilisateur.", e);
        }
        try {
            if (centreinteretId != null) {
                centreInteret = centreInteretRepository.findById(centreinteretId).get();
            }
        } catch (Exception e) {
            throw new CentreInteretException("Le centre d'intérêt n'existe pas.", e);
        }

        // Associating realisation to a planned and not realised yet activity
        if (activityId != null) {
            try {
                activity = activityRepository.findByIdAndEstRealisee(activityId, false);

            } catch (Exception e) {
                throw new ActivityException("L'activité associée n'existe pas.", e);
            }
            realisation = new Realisation(distanceRealisation, dateRealisation, activity, centreInteret);
            activity.setEstRealisee(true);
            programme.addRealisation(realisation);
        }

        // Creating a new activity for the realisation
        else {
            activity = new Activity(sport, distanceRealisation, programme, dateRealisation, centreInteret, true);
            realisation = new Realisation(distanceRealisation, dateRealisation, activity, centreInteret);
            programme.addActivity(activity);
            programme.addRealisation(realisation);
        }
        realisationRepository.save(realisation);
        return realisation;
    }
}
