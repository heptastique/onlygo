package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.DTO.ActivityDTO;
import smart.Entities.*;
import smart.Services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

@Service
public class ProgramActivities
{
    @Autowired
    private SportService sportService;

    @Autowired
    private TimeFrameService timeFrameService;

    @Autowired
    private CentreInteretService centreInteretService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private FindByJour findByJour;

    final double kDistanceUserToCentreInteretEvaluation = 0.0002;
    final double cDistanceUserToCentreInteretEvaluation = 1.0;

    class TimeFrameCentreInteret
    {
        private TimeFrame timeFrame;
        private CentreInteret centreInteret;
        private double evaluation;
    }

    public Programme calculate(User user)
    {
        // @TODO For each Sport
        // @TODO Split into multiple Activities

        double objectifHebdo = user.getObjectifHebdo();
        Point userLocation = user.getLocation();

        double distanceUserToCentreInteret;
        double distanceUserToCentreInteretEvaluation;
        double centreInteretEvaluation;
        TimeFrameCentreInteret timeFrameCentreInteret;
        TimeFrameCentreInteret bestTimeFrameCentreInteret;
        List <TimeFrameCentreInteret> timeFrameCentreInterets = new ArrayList <TimeFrameCentreInteret> ();

        Iterable <Sport> sports = sportService.getAllSports();
        Sport course = new Sport();
        for (Sport sport : sports)
        {
            if (sport.getNom().equals("Course"))
            {
                course = sport;
            }
        }

        Date currentMondayMidnight = new Date();
        while (findByJour.findDay(currentMondayMidnight) != Jour.LUNDI)
        {
            currentMondayMidnight.setDate(currentMondayMidnight.getDate() - 1);
        }
        currentMondayMidnight.setHours(0);
        currentMondayMidnight.setMinutes(0);
        currentMondayMidnight.setSeconds(0);

        // For each TimeFrame
        Iterable <TimeFrame> timeFrames = timeFrameService.getTimeFrameAll();
        for (TimeFrame timeFrame : timeFrames)
        {
            // For each CentreInteret
            Iterable<CentreInteret> centreInterets = centreInteretService.getCentreInteretAll();
            for (CentreInteret centreInteret : centreInterets)
            {
                // Create TimeFrameCentreInteret
                timeFrameCentreInteret = new TimeFrameCentreInteret();
                timeFrameCentreInteret.timeFrame = timeFrame;
                timeFrameCentreInteret.centreInteret = centreInteret;

                // Calculate Distance from User to CentreInteret
                distanceUserToCentreInteret = sqrt(pow(userLocation.getX() - centreInteret.getPoint().getX(), 2) +
                    pow(userLocation.getY() - centreInteret.getPoint().getY(), 2));

                // Calculate Evaluation of Distance from User to CentreInteret
                distanceUserToCentreInteretEvaluation = exp(-kDistanceUserToCentreInteretEvaluation * distanceUserToCentreInteret);

                // Calculate CentreInteret Evaluation
                centreInteretEvaluation = cDistanceUserToCentreInteretEvaluation * distanceUserToCentreInteretEvaluation / 1.0;

                // Calculate TimeFrameCentreInteret Evaluation
                timeFrameCentreInteret.evaluation = timeFrame.getEvaluation() * centreInteretEvaluation;

                // Insert TimeFrameCentreInteret in List, sorted by Evaluation
                int index = 0;
                while (index < timeFrameCentreInterets.size() && timeFrameCentreInterets.get(index).evaluation > timeFrameCentreInteret.evaluation)
                {
                    index = index + 1;
                }
                timeFrameCentreInterets.add(index, timeFrameCentreInteret);
            }
        }

        // Get best TimeFrameCentreInteret
        bestTimeFrameCentreInteret = timeFrameCentreInterets.get(0);

        // Create Activity
        ActivityDTO activity = new ActivityDTO();
        activity.setDate(bestTimeFrameCentreInteret.timeFrame.getDate());
        activity.setTimeFrameId(bestTimeFrameCentreInteret.timeFrame.getId());
        activity.setCentreinteretId(bestTimeFrameCentreInteret.centreInteret.getId());
        activity.setDistance((float)objectifHebdo);
        activity.setSportName(course.getNom());

        Activity savedActivity = activityService.addActivity(activity, false);

        //activity.setProgramme();

        // Create Activities
        List <Activity> activities = new ArrayList <Activity>();
        activities.add(savedActivity);

        // Create Program
        Programme programme = new Programme();
        programme.setUser(user);
        programme.setActivites(activities);
        programme.setDateDebut(currentMondayMidnight);

        programmeService.saveProgram(programme);

        return programme;
    }

    private ProgramActivities()
    {}

    /** Instance unique pré-initialisée */
    private static ProgramActivities INSTANCE = new ProgramActivities();

    /** Point d'accès pour l'instance unique du singleton */
    public static ProgramActivities getInstance()
    {
        return INSTANCE;
    }
}
