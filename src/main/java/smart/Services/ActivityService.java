package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.DTO.ActivityDTO;
import smart.Entities.*;
import smart.Repositories.ActivityRepository;
import smart.Repositories.CentreInteretRepository;
import smart.Repositories.SportRepository;
import smart.Repositories.TimeFrameRepository;
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
    CentreInteretRepository centreInteretRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private TimeFrameRepository timeFrameRepository;

    public Activity addActivity(ActivityDTO activityDTO, boolean realisee) {
        Activity activity = new Activity();
        activity.setDate(activityDTO.getDate());
        activity.setDistance(activityDTO.getDistance());
        // @TODO : support for program for activity creation
        String sportName = activityDTO.getSportName();
        Sport sport = sportRepository.findByNom(sportName);
        activity.setSport(sport);
        activity.setEstRealisee(realisee);
        activity.setCentreInteret(centreInteretRepository.findById(activityDTO.getCentreinteretId()).get());
        activity.setTimeFrame(timeFrameRepository.findById(activityDTO.getTimeFrameId()).get());
        return activityRepository.save(activity);
    }

    public Activity getNextActivity(Programme programme)
    {
        List<Activity> activities = programme.getActivites();
        Collections.sort(activities, comparing(Activity::getDate));

        // Get today's date
        Date todayDate = FindByJour.findCurrentDate();
        Jour jour = FindByJour.findDay(todayDate);
        // Get actual hour (by step of 3, from 0 to 24)
        int todayHour = FindByJour.findCurrentHour();
        todayHour = todayHour - todayHour%3;

        // Get actual timeframe
        TimeFrame timeFrame = timeFrameRepository.findByJourHour(jour,todayHour);

        for(Activity activity : activities)
        {
            if(activity.getDate().compareTo(todayDate)>=0
                && !activity.isEstRealisee()
                && (long)activity.getTimeFrame().getId()>=(long)timeFrame.getId())
                {
                    return activity;
                }
        }
        return null;
    }
}
