package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.DTO.ActivityDTO;
import smart.Entities.Activity;
import smart.Entities.Sport;
import smart.Repositories.ActivityRepository;
import smart.Repositories.SportRepository;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SportRepository sportRepository;

    public Activity addActivity(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setDate(activityDTO.getDate());
        activity.setDistance(activityDTO.getDistance());
        // @TODO : support for program for activity creation
        String sportName = activityDTO.getSportName();
        Sport sport = sportRepository.findByNom(sportName);
        activity.setSport(sport);
        return activityRepository.save(activity);
    }
}
