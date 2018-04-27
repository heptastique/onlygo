package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Entities.Activity;
import smart.Entities.Programme;
import smart.Entities.Realisation;
import smart.Entities.User;
import smart.Repositories.ActivityRepository;
import smart.Repositories.ProgrammeRepository;
import smart.Repositories.RealisationRepository;

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
}
