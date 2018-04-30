package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.DTO.ActivityDTO;
import smart.Entities.*;
import smart.Repositories.ActivityRepository;
import smart.Repositories.ProgrammeRepository;
import smart.Services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.cos;
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
    private ProgrammeRepository programmeRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private FindByJour findByJour;

    final double kDistanceUserToCentreInteretEvaluation = 0.0002;
    final double cDistanceUserToCentreInteretEvaluation = 1.0;

    // @TODO Max Distance/Duration of Activity for User
    final int distanceCourseMax = 5;

    class TimeFrameCentreInteret
    {
        private TimeFrame timeFrame;
        private CentreInteret centreInteret;
        private double evaluation;
    }

    public Programme calculate(User user)
    {
        double objectifHebdo = user.getObjectifHebdo();
        double objectifRemaining = objectifHebdo;
        Point userLocation = user.getLocation();

        double distanceUserToCentreInteret;
        double distanceUserToCentreInteretEvaluation;
        double centreInteretEvaluation;
        TimeFrameCentreInteret timeFrameCentreInteret;
        TimeFrameCentreInteret bestTimeFrameCentreInteret;
        List <TimeFrameCentreInteret> timeFrameCentreInterets = new ArrayList <TimeFrameCentreInteret> ();

        Sport course = sportService.getSport("Course");

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
                distanceUserToCentreInteret = sqrt(pow((userLocation.getX() - centreInteret.getPoint().getX()) * 111000, 2) +
                    pow((userLocation.getY() - centreInteret.getPoint().getY()) * 111000 * cos(userLocation.getX() - centreInteret.getPoint().getX()), 2));

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

        // Create Activities List
        List <Activity> activities = new ArrayList <Activity>();
        Activity savedActivity;

        // @TODO For each Sport

        // While Week Objective is not Completed
        int activityIndex = 0;
        int timeFrameCentreInteretIndex;
        while (objectifRemaining > 0 && activityIndex < timeFrameCentreInterets.size())
        {
            timeFrameCentreInteret = timeFrameCentreInterets.get(activityIndex);

            // Create Activity
            ActivityDTO activity = new ActivityDTO();
            activity.setDate(timeFrameCentreInteret.timeFrame.getDate());
            activity.setTimeFrameId(timeFrameCentreInteret.timeFrame.getId());
            activity.setCentreinteretId(timeFrameCentreInteret.centreInteret.getId());
            activity.setSportName(course.getNom());

            // Last Activity of Program
            if (objectifRemaining < distanceCourseMax)
            {
                activity.setDistance((float) objectifRemaining);
                objectifRemaining = 0;
            }
            // Need to add new Activity to the Program
            else
            {
                activity.setDistance((float) distanceCourseMax);
                objectifRemaining = objectifRemaining - distanceCourseMax;

                // Remove all incompatible TimeFrameCentreInteret (Same Day)
                timeFrameCentreInteretIndex = 0;
                while (timeFrameCentreInteretIndex < timeFrameCentreInterets.size())
                {
                    if (timeFrameCentreInterets.get(timeFrameCentreInteretIndex).timeFrame.getJour() == timeFrameCentreInteret.timeFrame.getJour())
                    {
                        timeFrameCentreInterets.remove(timeFrameCentreInteretIndex);
                    }
                    else
                    {
                        timeFrameCentreInteretIndex = timeFrameCentreInteretIndex + 1;
                    }
                }
            }

            // Add Activity to Activities List
            savedActivity = activityService.addActivity(activity, false);
            activities.add(savedActivity);

            activityIndex = activityIndex + 1;
        }

        // Create Program
        Programme programme = new Programme();
        programme.setUser(user);
        programme.setObjectifDistance(objectifHebdo);
        programme.setDateDebut(currentMondayMidnight);

        Programme exisitingProgramme = programmeRepository.findByUserAndDateDebut(user, currentMondayMidnight).get();

        if(exisitingProgramme != null) {
            for(Activity existingActivity : exisitingProgramme.getActivites()) {
                activityRepository.delete(existingActivity);
            }
            programme.setId(exisitingProgramme.getId());
        }

        programme = programmeService.saveProgram(programme);

        for(Activity activityTemp : activities) {
            activityTemp.setProgramme(programme);
            activityRepository.save(activityTemp);
        }

        programme.setActivites(activities);

        programme = programmeService.saveProgram(programme);

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
