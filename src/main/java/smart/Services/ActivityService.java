package smart.Services;

import javafx.scene.shape.Polyline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.ActivityItinerary;
import smart.Algorithms.FindByJour;
import smart.Algorithms.JsonToSQL;
import smart.DTO.ActivityDTO;
import smart.DTO.PointsDataDTO;
import smart.DTO.PolylinePointsDTO;
import smart.DTO.SegmentDTO;
import smart.Entities.*;
import smart.Repositories.*;

import java.util.*;

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
        activity.setDatePrevue(activityDTO.getDate());
        activity.setDistancePrevue(activityDTO.getDistance());
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
        Collections.sort(activities, comparing(Activity::getDatePrevue).thenComparingLong(Activity::getTimeFrameId));

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
            if(activity.getDatePrevue().compareTo(todayDate)>=0
                && !activity.isEstRealisee()
                && (long)activity.getTimeFrame().getId()>=(long)timeFrame.getId())
                {
                    return activity;
                }
        }
        return null;
    }
    public Activity getActivity( long id){
        Activity activity = activityRepository.findById(id).get();
        return activity;
    }
    public PolylinePointsDTO findItinary (User user, Activity activity, CentreInteret centreInteret){
        List<PointCentreInteret> itinerary = ActivityItinerary.findActivityItinerary(user, activity, centreInteret);
        PolylinePointsDTO polylinePointsDTO = new PolylinePointsDTO(itinerary);
        return polylinePointsDTO;
    }

}
