package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.DTO.ActivityDTO;
import smart.Entities.Activity;
import smart.Entities.Programme;
import smart.Entities.Sport;
import smart.Repositories.ActivityRepository;
import smart.Repositories.SportRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Comparator.comparing;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SportRepository sportRepository;

    public Activity addActivityRealisee(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        activity.setDate(activityDTO.getDate());
        activity.setDistance(activityDTO.getDistance());
        // @TODO : support for program for activity creation
        String sportName = activityDTO.getSportName();
        Sport sport = sportRepository.findByNom(sportName);
        activity.setSport(sport);
        activity.setEstRealisee(true);
        return activityRepository.save(activity);
    }

    public Activity getNextActivity(Programme programme)
    {
        List<Activity> activities = programme.getActivites();
        Collections.sort(activities, comparing(Activity::getDate));
        Date today = FindByJour.findBeginningOfToday();
        for(Activity activity : activities)
        {
            if(activity.getDate().compareTo(today)>=0 && !activity.isEstRealisee())
            {
                return activity;
            }
        }
        return null;
    }
}
